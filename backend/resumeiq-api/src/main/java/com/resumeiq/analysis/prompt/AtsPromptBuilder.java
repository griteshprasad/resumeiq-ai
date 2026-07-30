package com.resumeiq.analysis.prompt;

import org.springframework.stereotype.Component;

@Component
public class AtsPromptBuilder {

    public String buildPrompt(String resumeText,
                              String jobDescriptionText) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
You are an expert ATS (Applicant Tracking System) analyzer and Senior Technical Recruiter.

Your task is to compare the candidate's resume with the given job description.

Instructions:

1. Analyze the resume objectively.
2. Calculate an ATS score between 0 and 100.
3. Identify strengths.
4. Identify weaknesses.
5. Identify important missing keywords.
6. Suggest improvements for different resume sections.
7. Be factual.
8. Do not hallucinate.
9. Return ONLY valid JSON.
10. Do NOT use markdown.
11. Do NOT wrap the JSON inside ``` blocks.

The JSON format MUST be exactly:

{
  "atsFeedback": {
    "atsScore": 0,
    "summary": "",
    "strengths": [],
    "weaknesses": [],
    "missingKeywords": []
  },
  "improvements": [
    {
      "section": "",
      "issue": "",
      "suggestion": "",
      "reason": ""
    }
  ]
}

==========================
RESUME
==========================

""");

        prompt.append(resumeText);

        prompt.append("""

==========================
JOB DESCRIPTION
==========================

""");

        prompt.append(jobDescriptionText);

        return prompt.toString();

    }

}