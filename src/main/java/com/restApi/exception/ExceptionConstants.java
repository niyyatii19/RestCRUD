package com.restApi.exception;

public enum ExceptionConstants {
	CUSTOMER_NOT_FOUND("customer.not.found");
	
	private final String type;
	private ExceptionConstants(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
