package com.pwdgame.tools;

public interface IAppException {

	/**
	 * 当程序发生未捕获的异常
	 * @param ex
	 */
	public void onAppException(Throwable ex);
}
