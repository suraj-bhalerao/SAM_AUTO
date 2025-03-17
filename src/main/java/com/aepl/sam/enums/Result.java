package com.aepl.sam.enums;

public enum Result {
	PASS("PASS"),
	FAIL("FAIL"),
	ERROR("ERROR"),
	ISSUE("ISSUE");
	
	private final String value;
	
	Result(String string) {
		this.value = string;
	}
	
    public String getValue() {
        return value;
    }
}
