package com.example.mysmsmanager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

public class SmsSender {
	Context context;
	private SmsManager manager;
	public static final int REQUEST_IMAGE=323;
	public SmsSender(Context context) {
		this.context=context;
	}



	public void callToMessageAppToSendSMS(String phoneNumber, String content){
		Intent intent=new Intent(Intent.ACTION_VIEW);
		
		//intent.putExtra("address", "01635456498");
		intent.putExtra("sms_body", content);
		intent.setData(Uri.parse("sms:"+phoneNumber));
		
		context.startActivity(intent);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void getImage(){
		Intent intent=new Intent(Intent.ACTION_PICK);
		intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		((MainActivity)context).startActivityForResult(intent, REQUEST_IMAGE);
	}
		
	public void sendMMS(String phoneNumber, String content,Uri uri){
		Intent intent=new Intent(Intent.ACTION_SEND);
		intent.putExtra("address", phoneNumber);
		intent.putExtra("sms_body", content);
		//intent.setData(Uri.parse("sms:"+phoneNumber));
		intent.putExtra(Intent.EXTRA_STREAM, uri);
		intent.setType("image/*");
		context.startActivity(intent);
	}
	public void sendSMS(String phoneNumber, String content){
		manager=SmsManager.getDefault();
		//Create listener to listen if sms is sent to telecom successfully
		PendingIntent sendPI=PendingIntent.getBroadcast(context, 0, new Intent("SMS_SENT"), 0);
		//Create listener to listen if sms is sent to address successfully
		PendingIntent deliveredPI=PendingIntent.getBroadcast(context, 0, new Intent("SMS_DELIVERED"), 0);
		manager.sendTextMessage(phoneNumber, null, content, sendPI, deliveredPI);
	}
}
