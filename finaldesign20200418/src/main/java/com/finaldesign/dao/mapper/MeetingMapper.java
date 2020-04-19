package com.finaldesign.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.finaldesign.dao.entity.Meeting;

public interface MeetingMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Meeting record);

	Meeting selectByPrimaryKey(Integer id);

	List<Meeting> selectAllMeeting();
	
	Meeting selectActiveMeeting();

	List<Meeting> selectByTimeRange(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	int updateByPrimaryKeySelective(Meeting record);

	int updateByPrimaryKey(Meeting record);
}