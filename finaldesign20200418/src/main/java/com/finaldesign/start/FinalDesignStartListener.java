package com.finaldesign.start;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.baidu.aip.face.AipFace;
import com.finaldesign.group.DefaultGroup;

@Component
public class FinalDesignStartListener implements ApplicationRunner {
	@Autowired
	private AipFace aipFace;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		boolean existedGroup = false;
		HashMap<String, String> options = new HashMap<>();
		options.put("start", "0");
		options.put("length", "100");
		JSONObject result = aipFace.getGroupList(options);
		JSONArray groupLsit = result.getJSONObject("result").getJSONArray("group_id_list");
		for (int i = 0; i < groupLsit.length(); i++) {
			String groupId = groupLsit.getString(i);
			if (!existedGroup && groupId.equals(DefaultGroup.DEFAULT.getGroupId())) {
				existedGroup = true;
			}
		}

		if (!existedGroup) {
			aipFace.groupAdd(DefaultGroup.DEFAULT.getGroupId(), new HashMap<>());
		}
		
		aipFace.getGroupUsers(DefaultGroup.DEFAULT.getGroupId(), options);
	}

}
