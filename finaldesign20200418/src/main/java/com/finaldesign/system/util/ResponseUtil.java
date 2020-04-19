package com.finaldesign.system.util;

import java.util.HashMap;
import java.util.Map;

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
}
