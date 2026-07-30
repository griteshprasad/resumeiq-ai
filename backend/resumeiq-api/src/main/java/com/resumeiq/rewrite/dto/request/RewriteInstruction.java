package com.resumeiq.rewrite.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RewriteInstruction {

    @NotNull(message = "Section is required.")
    private ResumeSectionType section;

    @NotBlank(message = "Goal is required.")
    private String goal;

}