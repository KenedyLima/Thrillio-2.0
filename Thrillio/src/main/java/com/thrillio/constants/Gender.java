package com.thrillio.constants;

public enum Gender {

	MALE(0),
	FEMALE(1),
	TRANSGENDER(2);

	private int value;

	private Gender(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	

}
