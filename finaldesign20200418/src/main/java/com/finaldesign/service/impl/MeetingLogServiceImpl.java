package com.finaldesign.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finaldesign.dao.entity.MeetingLog;
import com.finaldesign.dao.mapper.MeetingLogMapper;
import com.finaldesign.dao.mapper.MeetingMapper;
import com.finaldesign.dao.mapper.SUserMapper;
import com.finaldesign.response.entity.MeetingLogDetail;
import com.finaldesign.service.MeetingLogService;

@Service
public class MeetingLogServiceImpl implements MeetingLogService {
	@Autowired
	private MeetingLogMapper meetingLogMapper;
	@Autowired
	private MeetingMapper meetingMapper;
	@Autowired
	private SUserMapper sUserMapper;

	@Override
	public MeetingLogDetail queryOneMeetingLog(Integer meetingLogId) {
		MeetingLog meetingLog = meetingLogMapper.selectByPrimaryKey(meetingLogId);

		MeetingLogDetail rlt = MeetingLogDetail.valueOf(meetingLog);
		rlt.setMeeting(meetingMapper.selectByPrimaryKey(meetingLog.getMeetingId()));
		rlt.setsUser(sUserMapper.selectByPrimaryKey(meetingLog.getsUserId()));
		return rlt;
	}

	@Override
	public List<MeetingLogDetail> queryAllMeetingLog() {
		List<MeetingLog> allMeetingLog = meetingLogMapper.selectAllMeetingLog();

		List<MeetingLogDetail> rlt = new ArrayList<>();
		for (MeetingLog meetingLog : allMeetingLog) {
			MeetingLogDetail item = MeetingLogDetail.valueOf(meetingLog);
			item.setMeeting(meetingMapper.selectByPrimaryKey(meetingLog.getMeetingId()));
			item.setsUser(sUserMapper.selectByPrimaryKey(meetingLog.getsUserId()));
			rlt.add(item);
		}
		return rlt;
	}

}
