package com.cg.obs.exceptions;

public class BankException extends Exception {
	private String message;

	public BankException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
