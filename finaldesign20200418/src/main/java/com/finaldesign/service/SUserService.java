package com.finaldesign.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.finaldesign.dao.entity.SUser;

public interface SUserService {

	@Transactional
	void createSUser(SUser sUser);

	@Transactional
	void deleteSUser(Integer sUserId);

	@Transactional
	void updateSUser(SUser sUser);

	SUser querySUser(Integer sUserId);

	List<SUser> queryAllSUser();
}
