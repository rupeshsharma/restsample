package com.my.sample.config.exception;

public class AccessDeniedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8947958910427039325L;
	private int status;

	public AccessDeniedException() {
	}

	public AccessDeniedException(String message) {
		super(message);
	}

	public AccessDeniedException(int status, String message) {
		super(message);
		this.status = status;
	}

	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getStatus() {
		return status;
	}
}
