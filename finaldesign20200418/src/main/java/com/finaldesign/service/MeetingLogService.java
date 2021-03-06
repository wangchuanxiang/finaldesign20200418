package com.finaldesign.service;

import java.util.List;

import com.finaldesign.dao.entity.SUser;
import com.finaldesign.response.entity.MeetingLogDetail;

public interface MeetingLogService {

	MeetingLogDetail queryOneMeetingLog(Integer meetingLogId);

	List<MeetingLogDetail> queryAllMeetingLog();

	SUser clockInMeeting(String ipAddr, String photo);
}
