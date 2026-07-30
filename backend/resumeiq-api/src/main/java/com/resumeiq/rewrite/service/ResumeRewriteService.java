package com.resumeiq.rewrite.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.resumeiq.ai.service.AiService;
import com.resumeiq.jobdescription.entity.JobDescription;
import com.resumeiq.jobdescription.service.JobDescriptionService;
import com.resumeiq.resume.entity.Resume;
import com.resumeiq.resume.service.ResumeService;
import com.resumeiq.rewrite.dto.request.RewriteResumeRequest;
import com.resumeiq.rewrite.dto.response.RewriteResumeResponse;
import com.resumeiq.rewrite.prompt.ResumeRewritePromptBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeRewriteService {

	private final ResumeService resumeService;

	private final JobDescriptionService jobDescriptionService;

	private final ResumeRewritePromptBuilder promptBuilder;

	private final AiService aiService;

	public RewriteResumeResponse rewrite(RewriteResumeRequest request, String email) {

		Resume resume = resumeService.getEntityById(request.getResumeId(), email);

		JobDescription jobDescription = jobDescriptionService.getEntityById(request.getJobDescriptionId(), email);

		String prompt = promptBuilder.buildPrompt(resume.getExtractedText(), jobDescription.getExtractedText(), request.getSection());

		return aiService.generateResponse(prompt, RewriteResumeResponse.class);

	}

}