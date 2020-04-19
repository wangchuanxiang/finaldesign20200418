package com.finaldesign.dao.mapper;

import com.finaldesign.dao.entity.Meeting;

public interface MeetingMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Meeting record);

	Meeting selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Meeting record);

	int updateByPrimaryKey(Meeting record);
}