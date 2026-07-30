package com.resumeiq.analysis.dto.response;

import lombok.Data;

@Data
public class ResumeImprovement {

    /**
     * Example:
     * Professional Summary
     * Experience
     * Skills
     */
    private String section;

    /**
     * Problem detected by AI.
     */
    private String issue;

    /**
     * Suggested replacement.
     */
    private String suggestion;

    /**
     * Why AI recommends this change.
     */
    private String reason;

}