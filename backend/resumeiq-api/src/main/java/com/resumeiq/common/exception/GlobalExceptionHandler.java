package com.resumeiq.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.resumeiq.ai.exception.AiPromptException;
import com.resumeiq.ai.exception.AiProviderException;
import com.resumeiq.ai.exception.AiResponseParsingException;
import com.resumeiq.common.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(ResourceNotFoundException ex) {

		ApiResponse<Void> response = new ApiResponse<>(false, ex.getMessage(), null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse<Void>> handleBadCredentials(BadCredentialsException ex) {

		ApiResponse<Void> response = new ApiResponse<>(false, "Invalid email or password.", null);

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}

	@ExceptionHandler(AiPromptException.class)
	public ResponseEntity<ApiResponse<Void>> handleAiPromptException(AiPromptException ex) {

		return ResponseEntity.badRequest().body(new ApiResponse<>(false, ex.getMessage(), null));
	}

	@ExceptionHandler(AiResponseParsingException.class)
	public ResponseEntity<ApiResponse<Void>> handleAiParsingException(AiResponseParsingException ex) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse<>(false, "Failed to process AI response.", null));
	}

	@ExceptionHandler(AiProviderException.class)
	public ResponseEntity<ApiResponse<Void>> handleAiProviderException(AiProviderException ex) {

		return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
				.body(new ApiResponse<>(false, "AI service is currently unavailable.", null));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {

		log.error("Unhandled exception", ex);

		ApiResponse<Void> response = new ApiResponse<>(false, "Something went wrong.", null);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}