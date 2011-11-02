package com.zombietank.email;

public enum Priority {
	HIGHEST(1), HIGH(2), NORMAL(3), LOW(4), LOWEST(5);
	
	private final int value;

	private Priority(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
