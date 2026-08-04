package com.resumeiq.ai.exception;

public class AiQuotaExceededException extends AiException {

	public AiQuotaExceededException(String message) {
		super(message);
	}

	public AiQuotaExceededException(String message, Throwable cause) {
		super(message, cause);
	}
}
