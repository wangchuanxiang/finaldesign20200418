package com.finaldesign.view.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finaldesign.dao.entity.SUser;
import com.finaldesign.service.SUserService;
import com.finaldesign.system.util.ResponseUtil;

@RequestMapping("s_user")
@RestController
public class SUserController {
	@Autowired
	private SUserService sUserService;

	@PostMapping("create_one_suser")
	public Map<Object, Object> createOneSUser(@RequestParam String uName, @RequestParam String telephone,
			@RequestParam String photo) {
		SUser sUser = new SUser();
		sUser.setCreateTime(new Date());
		sUser.setUpdateTime(new Date());
		sUser.setuName(uName);
		sUser.setuPhoto(photo);
		sUser.setuTel(telephone);
		sUserService.createSUser(sUser);

		return ResponseUtil.getResponseRlt();
	}

	@PostMapping("delete_one_suser")
	public Map<Object, Object> deleteOneSUser(Integer sUserId) {
		sUserService.deleteSUser(sUserId);
		return ResponseUtil.getResponseRlt();
	}

	@PostMapping("update_one_suser")
	public Map<Object, Object> updateOneSUser(@RequestParam Integer sUserId, String uName, String telephone,
			String photo) {
		SUser sUser = new SUser();
		sUser.setId(sUserId);
		sUser.setUpdateTime(new Date());
		sUser.setuName(uName);
		sUser.setuPhoto(photo);
		sUser.setuTel(telephone);
		sUserService.updateSUser(sUser);
		return ResponseUtil.getResponseRlt();
	}

	@PostMapping("query_one_suser")
	public Map<Object, Object> queryOneSUser(Integer sUserId) {
		SUser rlt = sUserService.querySUser(sUserId);
		return ResponseUtil.getResponseRlt(rlt);
	}

	@PostMapping("query_all_suser")
	public Map<Object, Object> queryAllSUser() {
		List<SUser> rlt = sUserService.queryAllSUser();
		return ResponseUtil.getResponseRlt(rlt);
	}
}
