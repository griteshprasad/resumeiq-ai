package com.resumeiq.analysis.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class AtsFeedback {

    /**
     * ATS score between 0 and 100.
     */
    private Integer atsScore;

    /**
     * One paragraph overall analysis.
     */
    private String summary;

    /**
     * Good things in the resume.
     */
    private List<String> strengths;

    /**
     * Weak points.
     */
    private List<String> weaknesses;

    /**
     * Keywords missing from resume.
     */
    private List<String> missingKeywords;

}