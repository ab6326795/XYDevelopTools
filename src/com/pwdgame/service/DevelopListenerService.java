package com.pwdgame.service;

import com.pwdgame.util.Logger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DevelopListenerService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate(){
		super.onCreate();
		
/*		for(int i=0;i<20;i++){
		   Logger.debug("MainLooper:"+getApplicationContext().getMainLooper());
		   try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}*/
	}
}
