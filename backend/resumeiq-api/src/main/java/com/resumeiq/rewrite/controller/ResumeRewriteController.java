package com.resumeiq.rewrite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.resumeiq.common.dto.ApiResponse;
import com.resumeiq.rewrite.dto.request.RewriteResumeRequest;
import com.resumeiq.rewrite.dto.response.RewriteResumeResponse;
import com.resumeiq.rewrite.service.ResumeRewriteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rewrite")
@RequiredArgsConstructor
@Validated
public class ResumeRewriteController {

	private final ResumeRewriteService rewriteService;

	@PostMapping
	public ResponseEntity<ApiResponse<RewriteResumeResponse>> rewriteResume(
			@Valid @RequestBody RewriteResumeRequest request, Authentication authentication) {

		RewriteResumeResponse response = rewriteService.rewrite(request, authentication.getName());

		return ResponseEntity.ok(new ApiResponse<>(true, "Resume section rewritten successfully.", response));
	}

}