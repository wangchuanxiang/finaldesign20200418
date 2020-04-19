package com.finaldesign.system.exception;

public enum SystemErr {
	UNKNOW("0001", "未知异常"),
	
	SUSER_NOT_EXISTED("0001", "用户不存在"),
	
	SUSER_EXISTED("0002", "用户已存在"),
	
	DATETIME_RANGE_ILLEGEL("0003", "时间范围不正确"),
	
	CURRENT_EXISTED_MEETING("0004", "与会议时间冲突"),
	
	MEETING_NOT_EXISTED("0005", "会议不存在"),
	
	MEETING_EXISTED("0006", "会议已存在"),
	
	NOT_FIND_ACTIVE_MEETING("0007", "未找到有效的会议"),
	
	CLOCK_IN_EXISTED("0008", "已完成会议打卡")
	
	;

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
