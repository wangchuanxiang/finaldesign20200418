package com.finaldesign.response.entity;

import com.finaldesign.dao.entity.Meeting;
import com.finaldesign.dao.entity.MeetingLog;
import com.finaldesign.dao.entity.SUser;

public class MeetingLogDetail extends MeetingLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SUser sUser;
	private Meeting meeting;

	public SUser getsUser() {
		return sUser;
	}

	public void setsUser(SUser sUser) {
		this.sUser = sUser;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public static MeetingLogDetail valueOf(MeetingLog meetingLog) {
		MeetingLogDetail rlt = new MeetingLogDetail();
		rlt.setCreateTime(meetingLog.getCreateTime());
		rlt.setId(meetingLog.getId());
		rlt.setIpAddr(meetingLog.getIpAddr());
		rlt.setMeetingId(meetingLog.getMeetingId());
		rlt.setsUserId(meetingLog.getsUserId());
		return rlt;
	}
}
