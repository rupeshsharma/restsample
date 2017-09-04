package com.my.sample.enums;

public enum PaymentType {
	CARD("Card"), CASH("Cash");
	private final String value;

	PaymentType(String value) {
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
