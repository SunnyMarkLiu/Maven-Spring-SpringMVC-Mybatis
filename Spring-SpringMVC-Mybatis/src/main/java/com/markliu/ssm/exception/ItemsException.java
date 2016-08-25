package com.markliu.ssm.exception;

/**
 * Author: markliu
 * Time  : 16-8-25 下午6:21
 */
public class ItemsException extends Exception {

	private String message;

	public ItemsException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
