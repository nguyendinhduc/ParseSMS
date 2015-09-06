package com.example.demo_asyntask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView txtCount;
	private MyAsynTask mAsyntask;
	private Handler mHandler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CommonVL.UPDATE_COUNTER:
				txtCount.setText(msg.arg1+"");
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtCount=(TextView)findViewById(R.id.txtCount);
		
		mAsyntask=new MyAsynTask(mHandler, this);
		mAsyntask.execute();
		
	}
}
