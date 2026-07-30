package com.resumeiq.resumeversion.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateResumeVersionRequest {

    @NotNull(message = "Resume Id is required.")
    private UUID resumeId;

    private String title;

}