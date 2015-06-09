package com.pwdgame.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.pwdgame.lang.SyncBase;
import com.pwdgame.utils.Utils;

public class AppCrashHandler implements UncaughtExceptionHandler{

	public static final String TAG = "AppCrashHandler";	
	public static final String ACTION_CARSH_EXCEPTION="com.pwdgame.developtools.CARSH_EXCEPTION";
	public static final String ACTION_CARSH_EXCEPTION_DATA="_data";
	public static final String PKG_NAME="com.pwdgame.developtools";
	public static final String SERVICE_CLASS_NAME="com.pwdgame.service.DevelopListenerService";
	
	private static AppCrashHandler mInstance = new AppCrashHandler();

	private Context mContext;
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	private IAppException mOnAppException;

    //用于格式化日期,作为日志文件名的一部分  
    //private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); 
    
	public AppCrashHandler(){

	}
	
    public static AppCrashHandler getInstance(){    	
    	return mInstance;
    }
    /**
     * 初始化要
     * @param mContext
     */
    public void init(Context mContext){
    	this.mContext=mContext;
        /*返回时，未捕获的异常终止一个线程的执行默认的异常处理程序。
         * 设置默认的未捕获异常处理程序。此处理的情况下调用任何线程死亡是由于未处理的异常。*/
        //获取系统默认的UncaughtException处理器  
    	mDefaultHandler=Thread.getDefaultUncaughtExceptionHandler();
    	//设置该CrashHandler为程序的默认处理器 
    	Thread.setDefaultUncaughtExceptionHandler(this);
    	
    	startDevelopToolsThirdService();
    }

    /**
     * 设置App异常捕获监听
     * @param mAppException
     */
    public void setOnAppExceptionListener(IAppException mAppException){
    	this.mOnAppException=mAppException;
    }
    
	/**
	 * 发送自定的异常消息到developTools
	 * @param exception
	 */
	public void sendCustomException(String exception){
		Intent mIntent=new Intent(ACTION_CARSH_EXCEPTION);
	    mIntent.putExtra(ACTION_CARSH_EXCEPTION_DATA, exception);
	    mContext.sendBroadcast(mIntent);			
	}
	
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if(!handleException(ex)&&mDefaultHandler!=null){
			//如果ex为null，就按默认处理 
			mDefaultHandler.uncaughtException(thread, ex);
		}else{
			if(mOnAppException!=null){
				mOnAppException.onAppException(ex);
			}
		}
	}
	
    /**
     * 如果手机安装有开发者工具APP，则启动它的后台服务
     */
    private void startDevelopToolsThirdService(){
    
    	if(Utils.isPkgInstalled(mContext, PKG_NAME)){
    		
    		Intent mIntent=new Intent();
    		mIntent.setComponent(new ComponentName(PKG_NAME, SERVICE_CLASS_NAME));
    		mContext.startService(mIntent);
    		//Utils.startApp(mContext, toolAppName);
    	}
    }
    
    
	private boolean handleException(Throwable ex){
	    if(ex==null)
	    	return false;
	    //处理异常,这里将异常发出去
	    String crash=getCrashInfo(ex);
	    sendCustomException(crash);	    
		return true;
	}

    /** 
     * 保存错误信息到文件中 
     *  
     * @param ex 
     * @return  返回文件名称,便于将文件传送到服务器 
     */  
    private String getCrashInfo(Throwable ex) {  
    	//StringBuffer sb = new StringBuffer();     
        Writer writer = new StringWriter();  
        
        PrintWriter printWriter = new PrintWriter(writer);
        
        ex.printStackTrace(printWriter);  
        Throwable cause = ex.getCause();  
        while (cause != null) {  
            cause.printStackTrace(printWriter);  
            cause = cause.getCause();            
        }  
        printWriter.close();  
        String result = writer.toString();  
        try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return result;
    }  

}
