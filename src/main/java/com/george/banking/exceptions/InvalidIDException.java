package com.george.banking.exceptions;

public class InvalidIDException extends RuntimeException {
	public InvalidIDException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
	public InvalidIDException(String errorMessage) {
		super(errorMessage);
	}
}
