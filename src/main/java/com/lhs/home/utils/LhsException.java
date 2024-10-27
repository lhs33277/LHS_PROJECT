package com.lhs.home.utils;

public class LhsException extends Exception{
	
	private static final long serialVersionUID = 1L;  // 직렬화 식별자 선언
	private String errorCode;
	
	public LhsException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	@Override
	public String toString() {
		return "ErrorCode : " + errorCode + ", Message : " + getMessage();
	}

}
