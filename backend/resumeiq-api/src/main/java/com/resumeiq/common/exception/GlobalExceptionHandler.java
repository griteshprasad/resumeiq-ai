package com.resumeiq.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.resumeiq.ai.exception.AiPromptException;
import com.resumeiq.ai.exception.AiProviderException;
import com.resumeiq.ai.exception.AiQuotaExceededException;
import com.resumeiq.ai.exception.AiResponseParsingException;
import com.resumeiq.common.dto.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

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

		log.error("AI Rewrite failed.", ex);
		
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
				.body(new ApiResponse<>(false, "AI service is currently unavailable.", null));
	}
	
	@ExceptionHandler(AiQuotaExceededException.class)
	public ResponseEntity<ApiResponse<Void>> handleAiProviderException(AiQuotaExceededException ex) {

		log.error("AI Quota exceeded.", ex);
		
		return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
				.body(new ApiResponse<>(false, "AI quota exceeded. Please try again later.", null));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {

		log.error("Unhandled exception", ex);

		ApiResponse<Void> response = new ApiResponse<>(false, "Something went wrong.", null);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}