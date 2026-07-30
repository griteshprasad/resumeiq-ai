package com.resumeiq.rewrite.dto.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RewriteResumeRequest {

    @NotNull
    private UUID resumeId;

    @NotNull
    private UUID jobDescriptionId;

    @Valid
    @NotEmpty
    private List<RewriteInstruction> instructions;

}