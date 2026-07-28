package com.resumeiq.common.service;

import org.springframework.stereotype.Service;

import com.resumeiq.common.dto.HealthResponse;

@Service
public class HealthService {

    public HealthResponse getHealth() {
        return new HealthResponse(
                "UP",
                "ResumeIQ API",
                "1.0.0"
        );
    }
}