package com.example.demo_asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;

public class MyAsynTask extends AsyncTask<String, Integer, Boolean> {

	private Handler mHander;
	private Context mContext;

	public MyAsynTask(Handler handler, Context context) {
		mHander = handler;
		mContext = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Boolean doInBackground(String... links) {
		int count = 0;
		while (count < 100) {
			count++;
			publishProgress(count);
			SystemClock.sleep(1000);
		}
		return true;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);

		Message msg = new Message();
		msg.what = CommonVL.UPDATE_COUNTER;
		msg.arg1 = values[0];
		msg.setTarget(mHander);
		msg.sendToTarget();
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if (result) {
			Toast.makeText(mContext, "Counting is done!", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
