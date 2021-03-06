package com.android.internal.telephony;

import android.content.res.Resources;
import android.os.AsyncResult;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.telephony.Rlog;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TelephonyDevController extends Handler {
    protected static final boolean DBG = true;
    private static final int EVENT_HARDWARE_CONFIG_CHANGED = 1;
    protected static final String LOG_TAG = "TDC";
    private static final Object mLock = new Object();
    protected static ArrayList<HardwareConfig> mModems = new ArrayList<>();
    protected static ArrayList<HardwareConfig> mSims = new ArrayList<>();
    private static Message sRilHardwareConfig;
    private static TelephonyDevController sTelephonyDevController;

    protected static void logd(String s) {
        Rlog.d(LOG_TAG, s);
    }

    protected static void loge(String s) {
        Rlog.e(LOG_TAG, s);
    }

    public static TelephonyDevController create() {
        TelephonyDevController telephonyDevController;
        synchronized (mLock) {
            if (sTelephonyDevController == null) {
                if (SystemProperties.get("ro.vendor.mtk_telephony_add_on_policy", OppoModemLogManager.DEFAULT_MODEMDUMP_POSTBACK).equals(OppoModemLogManager.DEFAULT_MODEMDUMP_POSTBACK)) {
                    try {
                        Class<?> clazz = Class.forName("com.mediatek.internal.telephony.MtkTelephonyDevController", false, ClassLoader.getSystemClassLoader());
                        Rlog.d(LOG_TAG, "class = " + clazz);
                        Constructor clazzConstructfunc = clazz.getConstructor(new Class[0]);
                        Rlog.d(LOG_TAG, "constructor function = " + clazzConstructfunc);
                        sTelephonyDevController = (TelephonyDevController) clazzConstructfunc.newInstance(new Object[0]);
                    } catch (Exception e) {
                        Rlog.e(LOG_TAG, "No MtkTelephonyDevController! Used AOSP for instead!");
                        sTelephonyDevController = new TelephonyDevController();
                    }
                } else {
                    sTelephonyDevController = new TelephonyDevController();
                }
                telephonyDevController = sTelephonyDevController;
            } else {
                throw new RuntimeException("TelephonyDevController already created!?!");
            }
        }
        return telephonyDevController;
    }

    public static TelephonyDevController getInstance() {
        TelephonyDevController telephonyDevController;
        synchronized (mLock) {
            if (sTelephonyDevController != null) {
                telephonyDevController = sTelephonyDevController;
            } else {
                throw new RuntimeException("TelephonyDevController not yet created!?!");
            }
        }
        return telephonyDevController;
    }

    /* access modifiers changed from: protected */
    public void initFromResource() {
        String[] hwStrings = Resources.getSystem().getStringArray(17236074);
        if (hwStrings != null) {
            for (String hwString : hwStrings) {
                HardwareConfig hw = new HardwareConfig(hwString);
                if (hw.type == 0) {
                    updateOrInsert(hw, mModems);
                } else if (hw.type == 1) {
                    updateOrInsert(hw, mSims);
                }
            }
        }
    }

    public TelephonyDevController() {
        initFromResource();
        mModems.trimToSize();
        mSims.trimToSize();
    }

    public void registerRIL(CommandsInterface cmdsIf) {
        cmdsIf.getHardwareConfig(sRilHardwareConfig);
        Message message = sRilHardwareConfig;
        if (message != null) {
            AsyncResult ar = (AsyncResult) message.obj;
            if (ar.exception == null) {
                handleGetHardwareConfigChanged(ar);
            }
        }
        cmdsIf.registerForHardwareConfigChanged(sTelephonyDevController, 1, null);
    }

    public static void unregisterRIL(CommandsInterface cmdsIf) {
        cmdsIf.unregisterForHardwareConfigChanged(sTelephonyDevController);
    }

    public void handleMessage(Message msg) {
        if (msg.what != 1) {
            loge("handleMessage: Unknown Event " + msg.what);
            return;
        }
        logd("handleMessage: received EVENT_HARDWARE_CONFIG_CHANGED");
        handleGetHardwareConfigChanged((AsyncResult) msg.obj);
    }

    protected static void updateOrInsert(HardwareConfig hw, ArrayList<HardwareConfig> list) {
        synchronized (mLock) {
            int size = list.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                HardwareConfig item = list.get(i);
                if (item.uuid.compareTo(hw.uuid) == 0) {
                    logd("updateOrInsert: removing: " + item);
                    list.remove(i);
                    break;
                }
                i++;
            }
            logd("updateOrInsert: inserting: " + hw);
            list.add(hw);
        }
    }

    /* access modifiers changed from: protected */
    public void handleGetHardwareConfigChanged(AsyncResult ar) {
        if (ar.exception != null || ar.result == null) {
            loge("handleGetHardwareConfigChanged - returned an error.");
            return;
        }
        List hwcfg = (List) ar.result;
        for (int i = 0; i < hwcfg.size(); i++) {
            HardwareConfig hw = (HardwareConfig) hwcfg.get(i);
            if (hw != null) {
                if (hw.type == 0) {
                    updateOrInsert(hw, mModems);
                } else if (hw.type == 1) {
                    updateOrInsert(hw, mSims);
                }
            }
        }
    }

    public static int getModemCount() {
        int count;
        synchronized (mLock) {
            count = mModems.size();
            logd("getModemCount: " + count);
        }
        return count;
    }

    public HardwareConfig getModem(int index) {
        synchronized (mLock) {
            if (mModems.isEmpty()) {
                loge("getModem: no registered modem device?!?");
                return null;
            } else if (index > getModemCount()) {
                loge("getModem: out-of-bounds access for modem device " + index + " max: " + getModemCount());
                return null;
            } else {
                logd("getModem: " + index);
                HardwareConfig hardwareConfig = mModems.get(index);
                return hardwareConfig;
            }
        }
    }

    public int getSimCount() {
        int count;
        synchronized (mLock) {
            count = mSims.size();
            logd("getSimCount: " + count);
        }
        return count;
    }

    public HardwareConfig getSim(int index) {
        synchronized (mLock) {
            if (mSims.isEmpty()) {
                loge("getSim: no registered sim device?!?");
                return null;
            } else if (index > getSimCount()) {
                loge("getSim: out-of-bounds access for sim device " + index + " max: " + getSimCount());
                return null;
            } else {
                logd("getSim: " + index);
                HardwareConfig hardwareConfig = mSims.get(index);
                return hardwareConfig;
            }
        }
    }

    public HardwareConfig getModemForSim(int simIndex) {
        synchronized (mLock) {
            if (!mModems.isEmpty()) {
                if (!mSims.isEmpty()) {
                    if (simIndex > getSimCount()) {
                        loge("getModemForSim: out-of-bounds access for sim device " + simIndex + " max: " + getSimCount());
                        return null;
                    }
                    logd("getModemForSim " + simIndex);
                    HardwareConfig sim = getSim(simIndex);
                    Iterator<HardwareConfig> it = mModems.iterator();
                    while (it.hasNext()) {
                        HardwareConfig modem = it.next();
                        if (modem.uuid.equals(sim.modemUuid)) {
                            return modem;
                        }
                    }
                    return null;
                }
            }
            loge("getModemForSim: no registered modem/sim device?!?");
            return null;
        }
    }

    public ArrayList<HardwareConfig> getAllSimsForModem(int modemIndex) {
        synchronized (mLock) {
            if (mSims.isEmpty()) {
                loge("getAllSimsForModem: no registered sim device?!?");
                return null;
            } else if (modemIndex > getModemCount()) {
                loge("getAllSimsForModem: out-of-bounds access for modem device " + modemIndex + " max: " + getModemCount());
                return null;
            } else {
                logd("getAllSimsForModem " + modemIndex);
                ArrayList<HardwareConfig> result = new ArrayList<>();
                HardwareConfig modem = getModem(modemIndex);
                Iterator<HardwareConfig> it = mSims.iterator();
                while (it.hasNext()) {
                    HardwareConfig sim = it.next();
                    if (sim.modemUuid.equals(modem.uuid)) {
                        result.add(sim);
                    }
                }
                return result;
            }
        }
    }

    public ArrayList<HardwareConfig> getAllModems() {
        ArrayList<HardwareConfig> modems;
        synchronized (mLock) {
            modems = new ArrayList<>();
            if (mModems.isEmpty()) {
                logd("getAllModems: empty list.");
            } else {
                Iterator<HardwareConfig> it = mModems.iterator();
                while (it.hasNext()) {
                    modems.add(it.next());
                }
            }
        }
        return modems;
    }

    public ArrayList<HardwareConfig> getAllSims() {
        ArrayList<HardwareConfig> sims;
        synchronized (mLock) {
            sims = new ArrayList<>();
            if (mSims.isEmpty()) {
                logd("getAllSims: empty list.");
            } else {
                Iterator<HardwareConfig> it = mSims.iterator();
                while (it.hasNext()) {
                    sims.add(it.next());
                }
            }
        }
        return sims;
    }
}
