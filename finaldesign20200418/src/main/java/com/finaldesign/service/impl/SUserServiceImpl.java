package com.finaldesign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finaldesign.dao.entity.SUser;
import com.finaldesign.dao.mapper.SUserMapper;
import com.finaldesign.service.SUserService;

@Service
public class SUserServiceImpl implements SUserService {
	@Autowired
	private SUserMapper sUserMapper;

	@Override
	public void createSUser(SUser sUser) {
		sUserMapper.insert(sUser);
	}

	@Override
	public void deleteSUser(Integer sUserId) {
		sUserMapper.deleteByPrimaryKey(sUserId);
	}

	@Override
	public void updateSUser(SUser sUser) {
		sUserMapper.updateByPrimaryKeySelective(sUser);
	}

	@Override
	public SUser querySUser(Integer sUserId) {
		return sUserMapper.selectByPrimaryKey(sUserId);
	}

	@Override
	public List<SUser> queryAllSUser() {
		return sUserMapper.selectAllSUser();
	}

}
