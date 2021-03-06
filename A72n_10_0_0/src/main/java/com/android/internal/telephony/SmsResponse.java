package com.android.internal.telephony;

import android.annotation.UnsupportedAppUsage;

public class SmsResponse {
    @UnsupportedAppUsage
    String mAckPdu;
    @UnsupportedAppUsage
    public int mErrorCode;
    @UnsupportedAppUsage
    public int mMessageRef;

    @UnsupportedAppUsage
    public SmsResponse(int messageRef, String ackPdu, int errorCode) {
        this.mMessageRef = messageRef;
        this.mAckPdu = ackPdu;
        this.mErrorCode = errorCode;
    }

    public String toString() {
        return "{ mMessageRef = " + this.mMessageRef + ", mErrorCode = " + this.mErrorCode + ", mAckPdu = " + this.mAckPdu + "}";
    }
}
