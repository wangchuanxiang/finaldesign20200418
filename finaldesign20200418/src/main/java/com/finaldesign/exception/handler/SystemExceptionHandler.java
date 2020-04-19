package com.finaldesign.exception.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.finaldesign.system.exception.FinalDesignException;
import com.finaldesign.system.util.ResponseUtil;

@ControllerAdvice
public class SystemExceptionHandler {

	@ExceptionHandler(FinalDesignException.class)
	@ResponseBody
	public Map<Object, Object> customException(FinalDesignException e) {
		return ResponseUtil.getResponseRlt(e);
	}

}
