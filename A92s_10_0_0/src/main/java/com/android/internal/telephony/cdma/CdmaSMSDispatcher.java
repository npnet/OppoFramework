package com.android.internal.telephony.cdma;

import android.annotation.UnsupportedAppUsage;
import android.os.Message;
import android.telephony.ServiceState;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.GsmCdmaPhone;
import com.android.internal.telephony.OppoRlog;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.telephony.SMSDispatcher;
import com.android.internal.telephony.SmsDispatchersController;
import com.android.internal.telephony.SmsHeader;
import com.android.internal.telephony.SmsMessageBase;
import com.android.internal.telephony.util.SMSDispatcherUtil;

public class CdmaSMSDispatcher extends SMSDispatcher {
    private static final String TAG = "CdmaSMSDispatcher";
    private static final boolean VDBG = false;

    public CdmaSMSDispatcher(Phone phone, SmsDispatchersController smsDispatchersController) {
        super(phone, smsDispatchersController);
        OppoRlog.Rlog.d(TAG, "CdmaSMSDispatcher created");
    }

    @Override // com.android.internal.telephony.SMSDispatcher
    @UnsupportedAppUsage
    public String getFormat() {
        return "3gpp2";
    }

    public void sendStatusReportMessage(SmsMessage sms) {
        sendMessage(obtainMessage(10, sms));
    }

    /* access modifiers changed from: protected */
    @Override // com.android.internal.telephony.SMSDispatcher
    public void handleStatusReport(Object o) {
        if (o instanceof SmsMessage) {
            handleCdmaStatusReport((SmsMessage) o);
        } else if (o != null && o.getClass() != null) {
            OppoRlog.Rlog.e(TAG, "handleStatusReport() called for object type " + o.getClass().getName());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.android.internal.telephony.SMSDispatcher
    public boolean shouldBlockSmsForEcbm() {
        return this.mPhone.isInEcm() && isCdmaMo() && !isIms();
    }

    /* access modifiers changed from: protected */
    @Override // com.android.internal.telephony.SMSDispatcher
    public SmsMessageBase.SubmitPduBase getSubmitPdu(String scAddr, String destAddr, String message, boolean statusReportRequested, SmsHeader smsHeader, int priority, int validityPeriod) {
        return SMSDispatcherUtil.getSubmitPduCdma(scAddr, destAddr, message, statusReportRequested, smsHeader, priority);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.internal.telephony.SMSDispatcher
    public SmsMessageBase.SubmitPduBase getSubmitPdu(String scAddr, String destAddr, int destPort, byte[] message, boolean statusReportRequested) {
        return SMSDispatcherUtil.getSubmitPduCdma(scAddr, destAddr, destPort, message, statusReportRequested);
    }

    /* access modifiers changed from: protected */
    @Override // com.android.internal.telephony.SMSDispatcher
    public GsmAlphabet.TextEncodingDetails calculateLength(CharSequence messageBody, boolean use7bitOnly) {
        return SMSDispatcherUtil.calculateLengthCdma(messageBody, use7bitOnly);
    }

    @UnsupportedAppUsage
    private void handleCdmaStatusReport(SmsMessage sms) {
        int i = 0;
        int count = this.deliveryPendingList.size();
        while (i < count) {
            SMSDispatcher.SmsTracker tracker = (SMSDispatcher.SmsTracker) this.deliveryPendingList.get(i);
            if (tracker.mMessageRef != sms.mMessageRef) {
                i++;
            } else if (((Boolean) this.mSmsDispatchersController.handleSmsStatusReport(tracker, getFormat(), sms.getPdu()).second).booleanValue()) {
                this.deliveryPendingList.remove(i);
                return;
            } else {
                return;
            }
        }
    }

    @Override // com.android.internal.telephony.SMSDispatcher
    public void sendSms(SMSDispatcher.SmsTracker tracker) {
        OppoRlog.Rlog.d(TAG, "sendSms:  isIms()=" + isIms() + " mRetryCount=" + tracker.mRetryCount + " mImsRetry=" + tracker.mImsRetry + " mMessageRef=" + tracker.mMessageRef + " mUsesImsServiceForIms=" + tracker.mUsesImsServiceForIms + " SS=" + this.mPhone.getServiceState().getState());
        if (handleSmsSendControl(tracker, this.mPhone, this.mContext, 1)) {
            OppoRlog.Rlog.d(TAG, "cdma--sendSms, stop tracker.");
            return;
        }
        int ss = this.mPhone.getServiceState().getState();
        boolean imsSmsDisabled = false;
        if (isIms() || ss == 0) {
            Message reply = obtainMessage(2, tracker);
            byte[] pdu = (byte[]) tracker.getData().get("pdu");
            int currentDataNetwork = this.mPhone.getServiceState().getDataNetworkType();
            if ((currentDataNetwork == 14 || (ServiceState.isLte(currentDataNetwork) && !this.mPhone.getServiceStateTracker().isConcurrentVoiceAndDataAllowed())) && this.mPhone.getServiceState().getVoiceNetworkType() == 7 && ((GsmCdmaPhone) this.mPhone).mCT.mState != PhoneConstants.State.IDLE) {
                imsSmsDisabled = true;
            }
            if ((tracker.mImsRetry != 0 || isIms()) && !imsSmsDisabled && !tracker.mUsesImsServiceForIms) {
                this.mCi.sendImsCdmaSms(pdu, tracker.mImsRetry, tracker.mMessageRef, reply);
                tracker.mImsRetry++;
                return;
            }
            this.mCi.sendCdmaSms(pdu, reply);
            return;
        }
        tracker.onFailed(this.mContext, getNotInServiceError(ss), 0);
    }
}
