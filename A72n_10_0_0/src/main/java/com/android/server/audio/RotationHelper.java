package com.android.server.audio;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.media.AudioSystem;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

/* access modifiers changed from: package-private */
public class RotationHelper {
    private static final String TAG = "AudioService.RotationHelper";
    private static Context sContext;
    private static int sDeviceRotation = 0;
    private static AudioDisplayListener sDisplayListener;
    private static Handler sHandler;
    private static final Object sRotationLock = new Object();

    RotationHelper() {
    }

    static void init(Context context, Handler handler) {
        if (context != null) {
            sContext = context;
            sHandler = handler;
            sDisplayListener = new AudioDisplayListener();
            enable();
            return;
        }
        throw new IllegalArgumentException("Invalid null context");
    }

    static void enable() {
        ((DisplayManager) sContext.getSystemService("display")).registerDisplayListener(sDisplayListener, sHandler);
        updateOrientation();
    }

    static void disable() {
        ((DisplayManager) sContext.getSystemService("display")).unregisterDisplayListener(sDisplayListener);
    }

    static void updateOrientation() {
        int newRotation = ((WindowManager) sContext.getSystemService("window")).getDefaultDisplay().getRotation();
        synchronized (sRotationLock) {
            if (newRotation != sDeviceRotation) {
                sDeviceRotation = newRotation;
                publishRotation(sDeviceRotation);
            }
        }
    }

    private static void publishRotation(int rotation) {
        Log.v(TAG, "publishing device rotation =" + rotation + " (x90deg)");
        if (rotation == 0) {
            AudioSystem.setParameters("rotation=0");
        } else if (rotation == 1) {
            AudioSystem.setParameters("rotation=90");
        } else if (rotation == 2) {
            AudioSystem.setParameters("rotation=180");
        } else if (rotation != 3) {
            Log.e(TAG, "Unknown device rotation");
        } else {
            AudioSystem.setParameters("rotation=270");
        }
    }

    /* access modifiers changed from: package-private */
    public static final class AudioDisplayListener implements DisplayManager.DisplayListener {
        AudioDisplayListener() {
        }

        public void onDisplayAdded(int displayId) {
        }

        public void onDisplayRemoved(int displayId) {
        }

        public void onDisplayChanged(int displayId) {
            RotationHelper.updateOrientation();
        }
    }
}