package com.thrillio.constants;

public enum UserType {
	

	
	 USER ("user"),
	 EDITOR ("editor"),
	 CHIEF_EDITOR ("chief editor");

	private String userType;

	UserType(String string) {
		this.userType = string;
	}
	
	public String getUserType() {
		return userType;
	}
}
