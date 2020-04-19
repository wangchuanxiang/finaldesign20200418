package com.finaldesign.system.util;

import java.util.HashMap;
import java.util.Map;

import com.finaldesign.system.exception.FinalDesignException;
import com.finaldesign.system.exception.SystemErr;

public class ResponseUtil {

	public static Map<Object, Object> getResponseRlt() {
		Map<Object, Object> rlt = new HashMap<Object, Object>();
		rlt.put("code", "0000");
		rlt.put("message", "OK");
		return rlt;
	}

	public static Map<Object, Object> getResponseRlt(Object result) {
		Map<Object, Object> rlt = new HashMap<Object, Object>();
		rlt.put("code", "0000");
		rlt.put("message", "OK");
		rlt.put("result", result);
		return rlt;
	}

	public static Map<Object, Object> getResponseRlt(SystemErr err) {
		Map<Object, Object> rlt = new HashMap<Object, Object>();
		rlt.put("code", err.getErrCode());
		rlt.put("message", err.getErrMsg());
		return rlt;
	}

	public static Map<Object, Object> getResponseRlt(FinalDesignException ex) {
		Map<Object, Object> rlt = new HashMap<Object, Object>();
		rlt.put("code", ex.getErrCode());
		rlt.put("message", ex.getErrMsg());
		return rlt;
	}
}
