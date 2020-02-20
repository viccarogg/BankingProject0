package com.george.banking.exceptions;

public class NoSuchAccountException extends RuntimeException {
	public NoSuchAccountException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
	public NoSuchAccountException(String errorMessage) {
		super(errorMessage);
	}
}
