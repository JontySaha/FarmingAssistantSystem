package com.cg.farming.exception;

public class NoAuthenticationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private static final String ERROR_MESSAGE = "terrible error";

	public NoAuthenticationException(String msg) {
		super(ERROR_MESSAGE);
	}
}

