package com.finaldesign.group;

public enum DefaultGroup {
	DEFAULT("final_design_group");

	private String groupId;

	private DefaultGroup(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
