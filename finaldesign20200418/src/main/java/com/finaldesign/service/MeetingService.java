package com.finaldesign.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.finaldesign.dao.entity.Meeting;

public interface MeetingService {
	@Transactional
	void createMeeting(Meeting meeting);

	@Transactional
	void deleteMeeting(Integer meetingId);

	@Transactional
	void updateMeeting(Meeting meeting);

	Meeting queryMeeting(Integer meetingId);

	List<Meeting> queryAllMeeting();
}
