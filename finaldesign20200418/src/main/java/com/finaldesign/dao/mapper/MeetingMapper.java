package com.finaldesign.dao.mapper;

import java.util.List;

import com.finaldesign.dao.entity.Meeting;

public interface MeetingMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Meeting record);

	Meeting selectByPrimaryKey(Integer id);
	
	List<Meeting> selectAllMeeting();

	int updateByPrimaryKeySelective(Meeting record);

	int updateByPrimaryKey(Meeting record);
}