package com.resumeiq.rewrite.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RewriteResumeRequest {

    @NotNull(message = "Resume Id is required.")
    private UUID resumeId;

    @NotNull(message = "Job Description Id is required.")
    private UUID jobDescriptionId;

    @NotNull(message = "Section is required.")
    private ResumeSectionType section;

}