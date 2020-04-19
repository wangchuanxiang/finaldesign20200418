package com.finaldesign.view.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finaldesign.system.util.ResponseUtil;

@RequestMapping("s_user")
@RestController
public class SUserController {

	@PostMapping("create_one_suser")
	public Map<Object, Object> createOneSUser(@RequestParam String uName, @RequestParam Integer age,
			@RequestParam String telephone, @RequestParam String photo) {
		
		return ResponseUtil.getResponseRlt();
	}

	@GetMapping("test")
	public Map<Object, Object> test() {

		return ResponseUtil.getResponseRlt();
	}
}
