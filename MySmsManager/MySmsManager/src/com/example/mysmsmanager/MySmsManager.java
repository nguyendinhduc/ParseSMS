package com.example.mysmsmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySmsManager extends BroadcastReceiver {
	final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {
		//Xu ly voi tin nhan dc gui den
		if (intent.getAction().equals(ACTION_SMS_RECEIVED)) {
			getSMSReveived(intent, context);
		} else {
			//xu ly khi gui tin nhan di
			switch (getResultCode()) {
			case Activity.RESULT_OK:
				// Sent sms to telecom successfully
				if (intent.getAction().equals("SMS_SENT")) {
					Toast.makeText(context, "SMS is sent", Toast.LENGTH_SHORT)
							.show();
				} else if (intent.getAction().equals("SMS_DELIVERED")) {
					Toast.makeText(context, "SMS is delivered",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				Toast.makeText(context, "RESULT_ERROR_GENERIC_FAILURE",
						Toast.LENGTH_SHORT).show();
				break;
			case SmsManager.RESULT_ERROR_NO_SERVICE:
				Toast.makeText(context, "RESULT_ERROR_NO_SERVICE",
						Toast.LENGTH_SHORT).show();
				break;
			case SmsManager.RESULT_ERROR_NULL_PDU:
				Toast.makeText(context, "RESULT_ERROR_NULL_PDU",
						Toast.LENGTH_SHORT).show();
				break;
			case SmsManager.RESULT_ERROR_RADIO_OFF:
				Toast.makeText(context, "RESULT_ERROR_RADIO_OFF",
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	}
	public void getSMSReveived(Intent intent,Context context){
		Bundle bundle=intent.getExtras();
		
		Object pdus[]=(Object[])bundle.get("pdus");
		if(pdus==null||pdus.length==0)
			return;
		SmsMessage message[]=new SmsMessage[pdus.length];
		for (int i = 0; i < message.length; i++) {
			message[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
		}
		if(message.length==0)
			return;
		String content="";
		String date;
		long when;
		String address;
		address=message[0].getDisplayOriginatingAddress();
		when=message[0].getTimestampMillis();
		
		date=getDate(when);
		for (int i = 0; i < message.length; i++) {
			content+=message[i].getMessageBody();
		}
		Toast.makeText(context, "Content: "+content+"\nDate: "+date+"\nAddress: "+address, Toast.LENGTH_SHORT).show();
		//--------------------
		
	}
	public String getDate(long when){
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(new Date(when));		
	}
}
