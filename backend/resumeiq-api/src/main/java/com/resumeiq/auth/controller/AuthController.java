package com.resumeiq.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resumeiq.auth.dto.request.RegisterRequest;
import com.resumeiq.auth.dto.response.RegisterResponse;
import com.resumeiq.auth.service.AuthService;
import com.resumeiq.common.dto.ApiResponse;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request) {

		RegisterResponse response = authService.register(request);

		return ResponseEntity.ok(
				new ApiResponse<>(
						true,
						"User registered successfully",
						response)
				);

	}

}