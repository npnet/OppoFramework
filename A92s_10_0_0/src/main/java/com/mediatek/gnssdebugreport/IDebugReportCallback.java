package com.mediatek.gnssdebugreport;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDebugReportCallback extends IInterface {
    void onDebugReportAvailable(Bundle bundle) throws RemoteException;

    public static class Default implements IDebugReportCallback {
        @Override // com.mediatek.gnssdebugreport.IDebugReportCallback
        public void onDebugReportAvailable(Bundle debugReport) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IDebugReportCallback {
        private static final String DESCRIPTOR = "com.mediatek.gnssdebugreport.IDebugReportCallback";
        static final int TRANSACTION_onDebugReportAvailable = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDebugReportCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDebugReportCallback)) {
                return new Proxy(obj);
            }
            return (IDebugReportCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                onDebugReportAvailable(_arg0);
                reply.writeNoException();
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IDebugReportCallback {
            public static IDebugReportCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.mediatek.gnssdebugreport.IDebugReportCallback
            public void onDebugReportAvailable(Bundle debugReport) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (debugReport != null) {
                        _data.writeInt(1);
                        debugReport.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().onDebugReportAvailable(debugReport);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDebugReportCallback impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IDebugReportCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
