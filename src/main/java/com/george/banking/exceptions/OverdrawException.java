package com.george.banking.exceptions;

public class OverdrawException extends RuntimeException {
	public OverdrawException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
	public OverdrawException(String errorMessage) {
		super(errorMessage);
	}
}
