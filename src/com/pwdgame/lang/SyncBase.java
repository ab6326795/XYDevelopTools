package com.pwdgame.lang;

public class SyncBase {
	final Object obj = new Object();
	
	protected void waitObj(){
		try {
			synchronized (obj) {
				obj.wait();
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	protected void notifyObj(){
		synchronized (obj) {
			obj.notify();
		}
	}
	
	protected void notifyAllObj(){
		synchronized (obj) {
			obj.notifyAll();
		}
	}
}
