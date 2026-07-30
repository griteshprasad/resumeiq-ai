package com.resumeiq.analysis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.resumeiq.analysis.dto.request.ResumeAnalysisRequest;
import com.resumeiq.analysis.dto.response.ResumeAnalysisResponse;
import com.resumeiq.analysis.service.ResumeAnalysisService;
import com.resumeiq.common.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/analysis")
@RequiredArgsConstructor
@Validated
public class ResumeAnalysisController {

    private final ResumeAnalysisService resumeAnalysisService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResumeAnalysisResponse>> analyzeResume( @Valid @RequestBody ResumeAnalysisRequest request, Authentication authentication) {

        ResumeAnalysisResponse response =resumeAnalysisService.analyzeResume(request, authentication.getName());

        return ResponseEntity.ok(new ApiResponse<>(true, "Resume analyzed successfully.", response));
    }

}