package com.finaldesign.dao.mapper;

import com.finaldesign.dao.entity.SUser;

public interface SUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SUser record);

	SUser selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SUser record);

	int updateByPrimaryKey(SUser record);
}