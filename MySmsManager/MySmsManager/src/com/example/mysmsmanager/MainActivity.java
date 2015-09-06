package com.example.mysmsmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{
	EditText sender,content;
	Button send;
	SmsSender smsSender;
	TelephonyManager telephony;
	MyPhoneStatesManager phoneStatesManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		send=(Button)findViewById(R.id.send);
		sender=(EditText)findViewById(R.id.sender);
		content=(EditText)findViewById(R.id.content);	
		//Init SMSSender to sendSMS and anything else...
		smsSender=new SmsSender(this);
		send.setOnClickListener(this);
		
		phoneStatesManager=new MyPhoneStatesManager(this);
		telephony=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		telephony.listen(phoneStatesManager, PhoneStateListener.LISTEN_CALL_STATE);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send:
			smsSender.sendSMS(sender.getText().toString(), content.getText().toString());
			//smsSender.getImage();
			break;
		default:
			break;
		}
	}	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==SmsSender.REQUEST_IMAGE&&resultCode==RESULT_OK){
			Uri uri=data.getData();
			smsSender.sendMMS(sender.getText().toString(), content.getText().toString(), uri);
		}
	}
}
