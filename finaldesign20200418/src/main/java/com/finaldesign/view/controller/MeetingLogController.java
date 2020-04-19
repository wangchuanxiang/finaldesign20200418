package com.finaldesign.view.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finaldesign.response.entity.MeetingLogDetail;
import com.finaldesign.service.MeetingLogService;
import com.finaldesign.system.util.ResponseUtil;

@RequestMapping("meeting_log")
@RestController
public class MeetingLogController {
	@Autowired
	private MeetingLogService meetingLogService;

	@PostMapping("query_one_meeting_log")
	public Map<Object, Object> queryOneMeetingLog(@RequestParam Integer meetingLogId) {
		MeetingLogDetail rlt = meetingLogService.queryOneMeetingLog(meetingLogId);
		return ResponseUtil.getResponseRlt(rlt);
	}

	@PostMapping("query_all_meeting_log")
	public Map<Object, Object> queryAllMeetingLog() {
		List<MeetingLogDetail> rlt = meetingLogService.queryAllMeetingLog();
		return ResponseUtil.getResponseRlt(rlt);
	}
}
