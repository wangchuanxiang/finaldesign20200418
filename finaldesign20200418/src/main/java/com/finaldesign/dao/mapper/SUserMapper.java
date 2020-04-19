package com.finaldesign.dao.mapper;

import java.util.List;

import com.finaldesign.dao.entity.SUser;

public interface SUserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SUser record);

	SUser selectByPrimaryKey(Integer id);
	
	List<SUser> selectAllSUser();

	int updateByPrimaryKeySelective(SUser record);

	int updateByPrimaryKeyWithBLOBs(SUser record);

	int updateByPrimaryKey(SUser record);
}