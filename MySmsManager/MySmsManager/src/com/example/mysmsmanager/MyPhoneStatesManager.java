package com.example.mysmsmanager;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyPhoneStatesManager extends PhoneStateListener{
	Context context;
	public MyPhoneStatesManager(Context context) {
		this.context=context;
	}
	@Override
	public void onCallStateChanged(int state, String incomingNumber) {
		// TODO Auto-generated method stub
		super.onCallStateChanged(state, incomingNumber);
		switch (state) {
		case TelephonyManager.CALL_STATE_IDLE:
			Toast.makeText(context, "Call state is idle", Toast.LENGTH_SHORT).show();
			break;
		case TelephonyManager.CALL_STATE_OFFHOOK:
			Toast.makeText(context, "Outgoing call is starting", Toast.LENGTH_SHORT).show();
			break;
		case TelephonyManager.CALL_STATE_RINGING:
			Toast.makeText(context, "Call is coming: "+incomingNumber, Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}
}
