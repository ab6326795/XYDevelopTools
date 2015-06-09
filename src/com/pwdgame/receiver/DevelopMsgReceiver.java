package com.pwdgame.receiver;

import com.pwdgame.developtools.AlertMsgActivity;
import com.pwdgame.tools.AppCrashHandler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DevelopMsgReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String action=intent.getAction();
		if(AppCrashHandler.ACTION_CARSH_EXCEPTION.equals(action)){
			//取出数据
			String carshData=intent.getStringExtra(AppCrashHandler.ACTION_CARSH_EXCEPTION_DATA);
			
			Intent mIntent=new Intent(context,AlertMsgActivity.class);
			mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mIntent.putExtra("_data", carshData);
			context.startActivity(mIntent);
		}
	}

}
