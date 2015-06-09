package com.pwdgame.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class Utils {

	/**
	 * 检查指定包名的APK是否安装
	 * @param mContext
	 * @param packName
	 * @return 如果是true,否则false
	 */
	public static boolean isPkgInstalled(Context mContext,String packName){
		boolean result=false;
		try {
			PackageInfo mInfo=mContext.getPackageManager().getPackageInfo(packName.toLowerCase(), 0);
			if(mInfo!=null){
				result=true;
			}
		} catch (NameNotFoundException e) {
			
		}
		
		return result;
	}
	
	/**
	 * 根据包名启动APP
	 * @param mContext
	 * @param pack
	 */
	public static void startApp(Context mContext,String pack){
		Intent newTask=mContext.getPackageManager().getLaunchIntentForPackage(pack);
		newTask.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mContext.startActivity(newTask);
	}
}
