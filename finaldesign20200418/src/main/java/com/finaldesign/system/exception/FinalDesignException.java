package com.finaldesign.system.exception;

public class FinalDesignException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String errCode;
	private final String errMsg;

	public FinalDesignException(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public FinalDesignException(SystemErr systemErr) {
		super();
		this.errCode = systemErr.getErrCode();
		this.errMsg = systemErr.getErrMsg();
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

}
