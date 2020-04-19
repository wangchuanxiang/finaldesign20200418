package com.finaldesign.dao.mapper;

import java.util.List;

import com.finaldesign.dao.entity.MeetingLog;

public interface MeetingLogMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MeetingLog record);

	MeetingLog selectByPrimaryKey(Integer id);
	
	List<MeetingLog> selectAllMeetingLog();

	int updateByPrimaryKeySelective(MeetingLog record);

	int updateByPrimaryKey(MeetingLog record);
}