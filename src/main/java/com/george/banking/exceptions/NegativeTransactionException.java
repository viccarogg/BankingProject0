package com.george.banking.exceptions;

public class NegativeTransactionException extends RuntimeException {
	public NegativeTransactionException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
	public NegativeTransactionException(String errorMessage) {
		super(errorMessage);
	}
}
