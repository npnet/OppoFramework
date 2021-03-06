package com.android.server.location;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.util.Preconditions;
import com.android.server.location.RemoteListenerHelper;
import java.util.HashMap;
import java.util.Map;

public abstract class RemoteListenerHelper<TListener extends IInterface> extends OppoBaseRemoteListenerHelper {
    protected static final int RESULT_GPS_LOCATION_DISABLED = 3;
    protected static final int RESULT_INTERNAL_ERROR = 4;
    protected static final int RESULT_NOT_ALLOWED = 6;
    protected static final int RESULT_NOT_AVAILABLE = 1;
    protected static final int RESULT_NOT_SUPPORTED = 2;
    protected static final int RESULT_SUCCESS = 0;
    protected static final int RESULT_UNKNOWN = 5;
    protected final AppOpsManager mAppOps;
    protected final Context mContext;
    protected final Handler mHandler;
    private boolean mHasIsSupported;
    private volatile boolean mIsRegistered;
    private boolean mIsSupported;
    private int mLastReportedResult = 5;
    private final Map<IBinder, RemoteListenerHelper<TListener>.IdentifiedListener> mListenerMap = new HashMap();
    private Handler mNmeaHandler;
    private final String mTag;

    /* access modifiers changed from: protected */
    public interface ListenerOperation<TListener extends IInterface> {
        void execute(TListener tlistener, CallerIdentity callerIdentity) throws RemoteException;
    }

    /* access modifiers changed from: protected */
    public abstract ListenerOperation<TListener> getHandlerOperation(int i);

    /* access modifiers changed from: protected */
    public abstract boolean isAvailableInPlatform();

    /* access modifiers changed from: protected */
    public abstract boolean isGpsEnabled();

    /* access modifiers changed from: protected */
    public abstract int registerWithService();

    /* access modifiers changed from: protected */
    public abstract void unregisterFromService();

    protected RemoteListenerHelper(Context context, Handler handler, String name) {
        super(context);
        Preconditions.checkNotNull(name);
        this.mHandler = handler;
        HandlerThread handlerThread = new HandlerThread("nmea_thread");
        handlerThread.start();
        this.mNmeaHandler = new Handler(handlerThread.getLooper());
        this.mTag = name;
        this.mContext = context;
        this.mAppOps = (AppOpsManager) context.getSystemService("appops");
    }

    public boolean isRegistered() {
        return this.mIsRegistered;
    }

    public void addListener(TListener listener, CallerIdentity callerIdentity) {
        int result;
        Preconditions.checkNotNull(listener, "Attempted to register a 'null' listener.");
        IBinder binder = listener.asBinder();
        synchronized (this.mListenerMap) {
            if (!this.mListenerMap.containsKey(binder)) {
                RemoteListenerHelper<TListener>.IdentifiedListener identifiedListener = new IdentifiedListener(listener, callerIdentity);
                this.mListenerMap.put(binder, identifiedListener);
                String str = this.mTag;
                Log.v(str, callerIdentity.mPackageName + " addListener, current listener size: " + this.mListenerMap.size());
                if (!isAvailableInPlatform()) {
                    result = 1;
                } else if (this.mHasIsSupported && !this.mIsSupported) {
                    result = 2;
                } else if (!isGpsEnabled()) {
                    result = 3;
                } else if (this.mHasIsSupported && this.mIsSupported) {
                    tryRegister();
                    result = 0;
                } else {
                    return;
                }
                post(identifiedListener, getHandlerOperation(result));
            }
        }
    }

    public void removeListener(TListener listener) {
        Preconditions.checkNotNull(listener, "Attempted to remove a 'null' listener.");
        synchronized (this.mListenerMap) {
            this.mListenerMap.remove(listener.asBinder());
            if (this.mListenerMap.isEmpty()) {
                tryUnregister();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void foreach(ListenerOperation<TListener> operation) {
        synchronized (this.mListenerMap) {
            foreachUnsafe(operation);
        }
    }

    /* access modifiers changed from: protected */
    public void foreachNmea(ListenerOperation<TListener> operation) {
        synchronized (this.mListenerMap) {
            foreachUnsafeNmea(operation);
        }
    }

    private void postNmea(RemoteListenerHelper<TListener>.IdentifiedListener identifiedListener, ListenerOperation<TListener> operation) {
        if (operation != null) {
            synchronized (this.mLock) {
                if (DEBUG) {
                    Log.v(this.mTag, "post runnable for current size: " + this.mInsertedRunnableCount);
                }
                if (this.mInsertedRunnableCount < 400) {
                    this.mNmeaHandler.post(new HandlerRunnable(identifiedListener, operation, true));
                    this.mInsertedRunnableCount++;
                } else {
                    if (this.mLogCount % 10 == 0) {
                        Log.d(this.mTag, "Skip post runnable due to size overflow size: " + this.mInsertedRunnableCount);
                        this.mLogCount = 0;
                    }
                    this.mLogCount++;
                }
            }
        }
    }

    private void foreachUnsafeNmea(ListenerOperation<TListener> operation) {
        for (RemoteListenerHelper<TListener>.IdentifiedListener identifiedListener : this.mListenerMap.values()) {
            postNmea(identifiedListener, operation);
        }
    }

    /* access modifiers changed from: protected */
    public void setSupported(boolean value) {
        synchronized (this.mListenerMap) {
            this.mHasIsSupported = true;
            this.mIsSupported = value;
        }
    }

    /* access modifiers changed from: protected */
    public void tryUpdateRegistrationWithService() {
        synchronized (this.mListenerMap) {
            if (!isGpsEnabled()) {
                tryUnregister();
            } else if (!this.mListenerMap.isEmpty()) {
                tryRegister();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateResult() {
        synchronized (this.mListenerMap) {
            int newResult = calculateCurrentResultUnsafe();
            if (this.mLastReportedResult != newResult) {
                foreachUnsafe(getHandlerOperation(newResult));
                this.mLastReportedResult = newResult;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasPermission(Context context, CallerIdentity callerIdentity) {
        return LocationPermissionUtil.doesCallerReportToAppOps(context, callerIdentity) ? this.mAppOps.checkOpNoThrow(1, callerIdentity.mUid, callerIdentity.mPackageName) == 0 : this.mAppOps.noteOpNoThrow(1, callerIdentity.mUid, callerIdentity.mPackageName) == 0;
    }

    /* access modifiers changed from: protected */
    public void logPermissionDisabledEventNotReported(String tag, String packageName, String event) {
        if (Log.isLoggable(tag, 3)) {
            Log.d(tag, "Location permission disabled. Skipping " + event + " reporting for app: " + packageName);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void foreachUnsafe(ListenerOperation<TListener> operation) {
        for (RemoteListenerHelper<TListener>.IdentifiedListener identifiedListener : this.mListenerMap.values()) {
            post(identifiedListener, operation);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.android.server.location.OppoBaseRemoteListenerHelper
    public void handlerPost(Runnable runnable) {
        Log.d(this.mTag, "on handlerPost!");
        this.mHandler.post(runnable);
    }

    private void post(RemoteListenerHelper<TListener>.IdentifiedListener identifiedListener, ListenerOperation<TListener> operation) {
        if (operation != null) {
            onPost(new HandlerRunnable(identifiedListener, operation));
        }
    }

    private void tryRegister() {
        this.mHandler.post(new Runnable() {
            /* class com.android.server.location.RemoteListenerHelper.AnonymousClass1 */
            int registrationState = 4;

            public void run() {
                if (!RemoteListenerHelper.this.mIsRegistered) {
                    this.registrationState = RemoteListenerHelper.this.registerWithService();
                    RemoteListenerHelper.this.mIsRegistered = this.registrationState == 0;
                }
                if (!RemoteListenerHelper.this.mIsRegistered) {
                    RemoteListenerHelper.this.mHandler.post(new Runnable() {
                        /* class com.android.server.location.$$Lambda$RemoteListenerHelper$1$zm4ubOjPyd7USwEJsdJwj1QLhgw */

                        public final void run() {
                            RemoteListenerHelper.AnonymousClass1.this.lambda$run$0$RemoteListenerHelper$1();
                        }
                    });
                }
            }

            public /* synthetic */ void lambda$run$0$RemoteListenerHelper$1() {
                synchronized (RemoteListenerHelper.this.mListenerMap) {
                    RemoteListenerHelper.this.foreachUnsafe(RemoteListenerHelper.this.getHandlerOperation(this.registrationState));
                }
            }
        });
    }

    private void tryUnregister() {
        this.mHandler.post(new Runnable() {
            /* class com.android.server.location.$$Lambda$RemoteListenerHelper$0Rlnad83RE1JdiVK0ULOLm530JM */

            public final void run() {
                RemoteListenerHelper.this.lambda$tryUnregister$0$RemoteListenerHelper();
            }
        });
    }

    public /* synthetic */ void lambda$tryUnregister$0$RemoteListenerHelper() {
        if (this.mIsRegistered) {
            unregisterFromService();
            this.mIsRegistered = false;
        }
    }

    private int calculateCurrentResultUnsafe() {
        if (!isAvailableInPlatform()) {
            return 1;
        }
        if (!this.mHasIsSupported || this.mListenerMap.isEmpty()) {
            return 5;
        }
        if (!this.mIsSupported) {
            return 2;
        }
        if (!isGpsEnabled()) {
            return 3;
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public class IdentifiedListener {
        private final CallerIdentity mCallerIdentity;
        private final TListener mListener;

        private IdentifiedListener(TListener listener, CallerIdentity callerIdentity) {
            this.mListener = listener;
            this.mCallerIdentity = callerIdentity;
        }
    }

    /* access modifiers changed from: private */
    public class HandlerRunnable implements Runnable {
        private final boolean isNmeaMessage;
        private final RemoteListenerHelper<TListener>.IdentifiedListener mIdentifiedListener;
        private final ListenerOperation<TListener> mOperation;

        private HandlerRunnable(RemoteListenerHelper<TListener>.IdentifiedListener identifiedListener, ListenerOperation<TListener> operation) {
            this.mIdentifiedListener = identifiedListener;
            this.mOperation = operation;
            this.isNmeaMessage = false;
        }

        private HandlerRunnable(RemoteListenerHelper<TListener>.IdentifiedListener identifiedListener, ListenerOperation<TListener> operation, boolean isNmeaMessage2) {
            this.mIdentifiedListener = identifiedListener;
            this.mOperation = operation;
            this.isNmeaMessage = isNmeaMessage2;
        }

        public void run() {
            try {
                RemoteListenerHelper.this.onRun();
                synchronized (RemoteListenerHelper.this.mLock) {
                    if (this.isNmeaMessage) {
                        if (OppoBaseRemoteListenerHelper.DEBUG) {
                            Log.v(RemoteListenerHelper.this.mTag, "pop runnable for current size: " + RemoteListenerHelper.this.mInsertedRunnableCount);
                        }
                        RemoteListenerHelper remoteListenerHelper = RemoteListenerHelper.this;
                        remoteListenerHelper.mInsertedRunnableCount--;
                    }
                }
                this.mOperation.execute((TListener) ((IdentifiedListener) this.mIdentifiedListener).mListener, ((IdentifiedListener) this.mIdentifiedListener).mCallerIdentity);
            } catch (RemoteException e) {
                Log.v(RemoteListenerHelper.this.mTag, "Error in monitored listener.", e);
            }
        }
    }
}
