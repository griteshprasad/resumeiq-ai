package com.resumeiq.resume.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.resumeiq.common.dto.ApiResponse;
import com.resumeiq.resume.dto.response.ResumeResponse;
import com.resumeiq.resume.service.ResumeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ResumeResponse>> upload(@RequestPart("file") MultipartFile file, Authentication authentication) {

        ResumeResponse response = resumeService.upload(file, authentication.getName());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Resume uploaded successfully.",
                        response));

    }

}