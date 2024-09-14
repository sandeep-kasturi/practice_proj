package com.dailycodebuffer.Springboot.tutorial.exception;

public class NotEmptyException extends Exception {
	public NotEmptyException(String msg) {
		super(msg);
	}
}
