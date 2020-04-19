package com.finaldesign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finaldesign.dao.entity.Meeting;
import com.finaldesign.dao.mapper.MeetingMapper;
import com.finaldesign.service.MeetingService;

@Service
public class MeetingServiceImpl implements MeetingService {
	@Autowired
	private MeetingMapper meetingMapper;

	@Override
	public void createMeeting(Meeting meeting) {
		meetingMapper.insert(meeting);
	}

	@Override
	public void deleteMeeting(Integer meetingId) {
		meetingMapper.deleteByPrimaryKey(meetingId);
	}

	@Override
	public void updateMeeting(Meeting meeting) {
		meetingMapper.updateByPrimaryKeySelective(meeting);
	}

	@Override
	public Meeting queryMeeting(Integer meetingId) {
		return meetingMapper.selectByPrimaryKey(meetingId);
	}

	@Override
	public List<Meeting> queryAllMeeting() {
		return meetingMapper.selectAllMeeting();
	}

}
