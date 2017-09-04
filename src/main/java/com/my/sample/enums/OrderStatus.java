package com.my.sample.enums;

public enum OrderStatus {
	PROCESSING("Processing"), COMPLETED("Completed");
	private final String value;

	OrderStatus(String value) {
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
