package com.finaldesign.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.aip.face.AipFace;
import com.finaldesign.dao.entity.Meeting;
import com.finaldesign.dao.entity.MeetingLog;
import com.finaldesign.dao.entity.SUser;
import com.finaldesign.dao.mapper.MeetingLogMapper;
import com.finaldesign.dao.mapper.MeetingMapper;
import com.finaldesign.dao.mapper.SUserMapper;
import com.finaldesign.group.DefaultGroup;
import com.finaldesign.response.entity.MeetingLogDetail;
import com.finaldesign.service.MeetingLogService;
import com.finaldesign.system.exception.FinalDesignException;
import com.finaldesign.system.exception.SystemErr;
import com.finaldesign.system.util.SystemUtil;

@Service
public class MeetingLogServiceImpl implements MeetingLogService {
	@Autowired
	private MeetingLogMapper meetingLogMapper;
	@Autowired
	private MeetingMapper meetingMapper;
	@Autowired
	private SUserMapper sUserMapper;
	@Autowired
	private AipFace aipFace;

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

	@Override
	public SUser clockInMeeting(String ipAddr, String photo) {
		Meeting activeMeeting = meetingMapper.selectActiveMeeting();
		if (activeMeeting == null)
			throw new FinalDesignException(SystemErr.NOT_FIND_ACTIVE_MEETING);

		HashMap<String, String> options = new HashMap<>();
		options.put("match_threshold", "80");
		options.put("quality_control", "LOW");

		String imagePhoto = SystemUtil.formatBase64Image(photo);
		JSONObject searchObj = aipFace.search(imagePhoto, "BASE64", DefaultGroup.DEFAULT.getGroupId(), options);
		if (searchObj.getInt("error_code") != 0)
			throw new FinalDesignException(searchObj.getInt("error_code"), searchObj.getString("error_msg"));
		JSONArray allUserList = searchObj.getJSONObject("result").getJSONArray("user_list");
		String userId = allUserList.getJSONObject(0).getString("user_id").replaceAll("userid_", "");

		SUser loadSUser = null;
		try {
			loadSUser = sUserMapper.selectByPrimaryKey(Integer.valueOf(userId));
			if (loadSUser == null)
				throw new FinalDesignException(SystemErr.SUSER_NOT_EXISTED);
		} catch (Exception e) {
			throw new FinalDesignException(SystemErr.SUSER_NOT_EXISTED);
		}

		List<MeetingLog> allMeetingLog = meetingLogMapper.selectByMeetingAndSUser(activeMeeting.getId(),
				loadSUser.getId());
		if (!allMeetingLog.isEmpty())
			throw new FinalDesignException(SystemErr.CLOCK_IN_EXISTED);

		MeetingLog meetingLog = new MeetingLog();
		meetingLog.setCreateTime(new Date());
		meetingLog.setMeetingId(activeMeeting.getId());
		meetingLog.setsUserId(loadSUser.getId());
		meetingLog.setIpAddr(ipAddr);
		meetingLogMapper.insert(meetingLog);
		
		return loadSUser;
	}

}
