package com.resumeiq.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resumeiq.common.dto.ApiResponse;
import com.resumeiq.common.dto.HealthResponse;
import com.resumeiq.common.service.HealthService;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<HealthResponse>> getHealth() {

        HealthResponse response = healthService.getHealth();

        ApiResponse<HealthResponse> apiResponse =
                new ApiResponse<>(
                        true,
                        "Application is healthy",
                        response);

        return ResponseEntity.ok(apiResponse);
    }
}