package com.resumeiq.jobdescription.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.resumeiq.common.dto.ApiResponse;
import com.resumeiq.jobdescription.dto.response.JobDescriptionResponse;
import com.resumeiq.jobdescription.service.JobDescriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/jobDescriptions")
@RequiredArgsConstructor
public class JobDescriptionController {

    private final JobDescriptionService jobDescriptionService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<JobDescriptionResponse>> upload(@RequestPart("file") MultipartFile file, Authentication authentication) {

        JobDescriptionResponse response = jobDescriptionService.upload(file, authentication.getName());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "JobDescription uploaded successfully.",
                        response));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<JobDescriptionResponse>>> getAll(Authentication authentication) {

        List<JobDescriptionResponse> response = jobDescriptionService.getAll(authentication.getName());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "JobDescriptions fetched successfully.",
                        response));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobDescriptionResponse>> getById(@PathVariable UUID id, Authentication authentication) {

        JobDescriptionResponse response =
                jobDescriptionService.getById(
                        id,
                        authentication.getName());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "JobDescription fetched successfully.",
                        response));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id, Authentication authentication) {

        jobDescriptionService.delete(
                id,
                authentication.getName());

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "JobDescription deleted successfully.",
                        null));
    }

}
