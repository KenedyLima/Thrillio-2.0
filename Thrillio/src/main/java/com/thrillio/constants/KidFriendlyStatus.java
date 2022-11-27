package com.thrillio.constants;

public enum KidFriendlyStatus {

	APPROVED("approved"), REJECTED("rejected"), UNKNOWN("unknown");
	
	private String status;

	KidFriendlyStatus (String status){
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
