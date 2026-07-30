package com.resumeiq.analysis.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resumeiq.ai.service.AiService;
import com.resumeiq.analysis.dto.request.ResumeAnalysisRequest;
import com.resumeiq.analysis.dto.response.ResumeAnalysisResponse;
import com.resumeiq.analysis.prompt.AtsPromptBuilder;
import com.resumeiq.jobdescription.entity.JobDescription;
import com.resumeiq.jobdescription.service.JobDescriptionService;
import com.resumeiq.resume.entity.Resume;
import com.resumeiq.resume.service.ResumeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeAnalysisService {

    private final ResumeService resumeService;

    private final JobDescriptionService jobDescriptionService;

    private final AtsPromptBuilder atsPromptBuilder;

    private final AiService aiService;

    private final ObjectMapper objectMapper;

    public ResumeAnalysisResponse analyzeResume(ResumeAnalysisRequest request, String email) {

    	Resume resume = resumeService.getEntityById(request.getResumeId(), email);

    	JobDescription jobDescription = jobDescriptionService.getEntityById(request.getJobDescriptionId(), email);

        String prompt = atsPromptBuilder.buildPrompt(resume.getExtractedText(), jobDescription.getExtractedText());

        String aiResponse = aiService.generateResponse(prompt);

        try {

            return objectMapper.readValue(aiResponse, ResumeAnalysisResponse.class);

        } catch (Exception ex) {
            throw new RuntimeException("Unable to parse AI response.", ex);
        }

    }

}