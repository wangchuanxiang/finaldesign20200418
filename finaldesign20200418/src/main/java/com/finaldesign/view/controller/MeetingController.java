package com.finaldesign.view.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finaldesign.dao.entity.Meeting;
import com.finaldesign.service.MeetingService;
import com.finaldesign.system.util.ResponseUtil;

@RequestMapping("meeting")
@RestController
public class MeetingController {
	@Autowired
	private MeetingService meetingService;

	@PostMapping("create_one_meeting")
	public Map<Object, Object> createOneMeeting(@RequestParam String mName, @RequestParam Date beginTime,
			@RequestParam Date endTime, @RequestParam String comment) {

		Meeting meeting = new Meeting();
		meeting.setBeginTime(beginTime);
		meeting.setEndTime(endTime);
		meeting.setCreateTime(new Date());
		meeting.setUpdateTime(new Date());
		meeting.setmName(mName);
		meeting.setComment(comment);
		meetingService.createMeeting(meeting);

		return ResponseUtil.getResponseRlt();
	}

	@PostMapping("delete_one_meeting")
	public Map<Object, Object> deleteOneMeeting(@RequestParam Integer meetingId) {
		meetingService.deleteMeeting(meetingId);
		return ResponseUtil.getResponseRlt();
	}

	@PostMapping("update_one_meeting")
	public Map<Object, Object> updateOneMeeting(@RequestParam Integer meetingId, String mName, Date beginTime,
			Date endTime, String comment) {
		Meeting meeting = new Meeting();
		meeting.setId(meetingId);
		meeting.setBeginTime(beginTime);
		meeting.setEndTime(endTime);
		meeting.setUpdateTime(new Date());
		meeting.setmName(mName);
		meeting.setComment(comment);
		meetingService.updateMeeting(meeting);
		return ResponseUtil.getResponseRlt();
	}

	@PostMapping("query_one_meeting")
	public Map<Object, Object> queryOneMeeting(@RequestParam Integer meetingId) {
		Meeting rlt = meetingService.queryMeeting(meetingId);
		return ResponseUtil.getResponseRlt(rlt);
	}

	@PostMapping("query_all_meeting")
	public Map<Object, Object> queryAllMeeting() {
		List<Meeting> rlt = meetingService.queryAllMeeting();
		return ResponseUtil.getResponseRlt(rlt);
	}
}
