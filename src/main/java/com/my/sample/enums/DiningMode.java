package com.my.sample.enums;

public enum DiningMode {
	TAKE_AWAY("Take Away"), DINE_IN("Dine In");

	private final String value;

	DiningMode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
