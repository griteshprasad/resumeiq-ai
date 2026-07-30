package com.resumeiq.analysis.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResumeAnalysisRequest {

    @NotNull(message = "Resume Id is required.")
    private UUID resumeId;

    @NotNull(message = "Job Description Id is required.")
    private UUID jobDescriptionId;

}