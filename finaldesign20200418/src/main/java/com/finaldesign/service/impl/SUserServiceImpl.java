package com.finaldesign.service.impl;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.aip.face.AipFace;
import com.finaldesign.dao.entity.SUser;
import com.finaldesign.dao.mapper.SUserMapper;
import com.finaldesign.group.DefaultGroup;
import com.finaldesign.service.SUserService;
import com.finaldesign.system.exception.FinalDesignException;
import com.finaldesign.system.exception.SystemErr;

@Service
public class SUserServiceImpl implements SUserService {
	private static final String PRE = "data:image/jpeg;base64,";
	@Autowired
	private SUserMapper sUserMapper;
	@Autowired
	private AipFace aipFace;

	@Override
	public void createSUser(SUser sUser) {
		sUserMapper.insert(sUser);

		HashMap<String, String> options = new HashMap<>();
		options.put("quality_control", "LOW");
		options.put("liveness_control", "NONE");
		options.put("action_type", "APPEND");
		String imagePhoto = sUser.getuPhoto().replaceAll(PRE, "");
		String userId = "userid_" + String.valueOf(sUser.getId());
		JSONObject createUserObj = aipFace.addUser(imagePhoto, "BASE64", DefaultGroup.DEFAULT.getGroupId(), userId,
				options);
		if (createUserObj.getInt("error_code") != 0)
			throw new FinalDesignException(createUserObj.getInt("error_code"), createUserObj.getString("error_msg"));
	}

	@Override
	public void deleteSUser(Integer sUserId) {
		SUser loadSUser = sUserMapper.selectByPrimaryKey(sUserId);
		if (loadSUser == null)
			throw new FinalDesignException(SystemErr.SUSER_NOT_EXISTED);

		JSONObject deleteUserObj = aipFace.deleteUser(DefaultGroup.DEFAULT.getGroupId(),
				"userid_" + String.valueOf(sUserId), new HashMap<>());
		if (deleteUserObj.getInt("error_code") != 0)
			throw new FinalDesignException(deleteUserObj.getInt("error_code"), deleteUserObj.getString("error_msg"));
		sUserMapper.deleteByPrimaryKey(sUserId);
	}

	@Override
	public void updateSUser(SUser sUser) {
		SUser loadSUser = sUserMapper.selectByPrimaryKey(sUser.getId());
		if (loadSUser == null)
			throw new FinalDesignException(SystemErr.SUSER_NOT_EXISTED);

		HashMap<String, String> options = new HashMap<>();
		String imagePhoto = sUser.getuPhoto().replaceAll(PRE, "");
		String userId = "userid_" + String.valueOf(sUser.getId());
		JSONObject updateUserObj = aipFace.updateUser(imagePhoto, "BASE64", DefaultGroup.DEFAULT.getGroupId(), userId,
				options);
		if (updateUserObj.getInt("error_code") != 0)
			throw new FinalDesignException(updateUserObj.getInt("error_code"), updateUserObj.getString("error_msg"));

		sUserMapper.updateByPrimaryKeySelective(sUser);
	}

	@Override
	public SUser querySUser(Integer sUserId) {
		SUser loadSUser = sUserMapper.selectByPrimaryKey(sUserId);
		if (loadSUser == null)
			throw new FinalDesignException(SystemErr.SUSER_NOT_EXISTED);
		return loadSUser;
	}

	@Override
	public List<SUser> queryAllSUser() {
		return sUserMapper.selectAllSUser();
	}

}
