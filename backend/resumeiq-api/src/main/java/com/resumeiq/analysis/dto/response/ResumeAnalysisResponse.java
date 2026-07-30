package com.resumeiq.analysis.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ResumeAnalysisResponse {

    private AtsFeedback atsFeedback;

    private List<ResumeImprovement> improvements;

}