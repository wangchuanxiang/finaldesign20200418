package com.finaldesign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finaldesign.dao.entity.Meeting;
import com.finaldesign.dao.mapper.MeetingMapper;
import com.finaldesign.service.MeetingService;
import com.finaldesign.system.exception.FinalDesignException;
import com.finaldesign.system.exception.SystemErr;

@Service
public class MeetingServiceImpl implements MeetingService {
	@Autowired
	private MeetingMapper meetingMapper;

	@Override
	public void createMeeting(Meeting meeting) {
		if (meeting.getBeginTime().after(meeting.getEndTime()))
			throw new FinalDesignException(SystemErr.DATETIME_RANGE_ILLEGEL);
		List<Meeting> allActiveMeeting = meetingMapper.selectByTimeRange(meeting.getBeginTime(), meeting.getEndTime());
		if (!allActiveMeeting.isEmpty())
			throw new FinalDesignException(SystemErr.CURRENT_EXISTED_MEETING);

		meetingMapper.insert(meeting);
	}

	@Override
	public void deleteMeeting(Integer meetingId) {
		Meeting loadMeeting = meetingMapper.selectByPrimaryKey(meetingId);
		if (loadMeeting == null)
			throw new FinalDesignException(SystemErr.MEETING_NOT_EXISTED);

		meetingMapper.deleteByPrimaryKey(meetingId);
	}

	@Override
	public void updateMeeting(Meeting meeting) {
		Meeting loadMeeting = meetingMapper.selectByPrimaryKey(meeting.getId());
		if (loadMeeting == null)
			throw new FinalDesignException(SystemErr.MEETING_NOT_EXISTED);

		List<Meeting> allActiveMeeting = meetingMapper.selectByTimeRange(meeting.getBeginTime(), meeting.getEndTime());
		if (allActiveMeeting.size() > 1 && !allActiveMeeting.get(0).getId().equals(meeting.getId()))
			throw new FinalDesignException(SystemErr.CURRENT_EXISTED_MEETING);
		meetingMapper.updateByPrimaryKeySelective(meeting);
	}

	@Override
	public Meeting queryMeeting(Integer meetingId) {
		Meeting loadMeeting = meetingMapper.selectByPrimaryKey(meetingId);
		if (loadMeeting == null)
			throw new FinalDesignException(SystemErr.MEETING_NOT_EXISTED);
		return loadMeeting;
	}

	@Override
	public List<Meeting> queryAllMeeting() {
		return meetingMapper.selectAllMeeting();
	}

}
