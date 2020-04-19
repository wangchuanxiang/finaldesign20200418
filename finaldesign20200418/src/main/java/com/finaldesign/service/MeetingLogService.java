package com.finaldesign.service;

import java.util.List;

import com.finaldesign.response.entity.MeetingLogDetail;

public interface MeetingLogService {

	MeetingLogDetail queryOneMeetingLog(Integer meetingLogId);

	List<MeetingLogDetail> queryAllMeetingLog();

	void clockInMeeting(String ipAddr, String photo);
}
