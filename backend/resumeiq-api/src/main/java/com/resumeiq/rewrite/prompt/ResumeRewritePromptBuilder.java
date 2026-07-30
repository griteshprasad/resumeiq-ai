package com.resumeiq.rewrite.prompt;

import org.springframework.stereotype.Component;

import com.resumeiq.rewrite.dto.request.ResumeSectionType;

@Component
public class ResumeRewritePromptBuilder {

    public String buildPrompt(String resumeText, String jobDescriptionText, ResumeSectionType section) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
You are an expert resume writer, ATS specialist and technical recruiter.

Your task is to rewrite ONLY the requested resume section.

Rules:

1. Rewrite ONLY the requested section.
2. Do NOT rewrite any other section.
3. Optimize for ATS.
4. Include relevant keywords from the Job Description naturally.
5. Do not invent fake experience.
6. Do not exaggerate.
7. Keep the candidate's experience truthful.
8. Improve grammar and readability.
9. Return ONLY valid JSON.
10. Do NOT use markdown.
11. Do NOT wrap the response inside ```.

The JSON MUST be exactly:

{
    "rewrittenContent":"",
    "explanation":""
}

Requested Resume Section:

""");

        prompt.append(section.name());

        prompt.append("""

=========================================
RESUME
=========================================

""");

        prompt.append(resumeText);

        prompt.append("""

=========================================
JOB DESCRIPTION
=========================================

""");

        prompt.append(jobDescriptionText);

        return prompt.toString();

    }

}