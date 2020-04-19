package com.finaldesign.view.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finaldesign.system.util.ResponseUtil;

@RestController
public class SUserController {

	@PostMapping("demo")
	public Map<Object, Object> demo() {
		
		return ResponseUtil.getResponseRlt();
	}
	
	@GetMapping("test")
	public Map<Object, Object> test() {
		
		return ResponseUtil.getResponseRlt();
	}
}
