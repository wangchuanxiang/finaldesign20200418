package com.finaldesign.system.exception;

public enum SystemErr {
	UNKNOW("0001", "未知异常");

	private final String errCode;
	private final String errMsg;

	private SystemErr(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

}
