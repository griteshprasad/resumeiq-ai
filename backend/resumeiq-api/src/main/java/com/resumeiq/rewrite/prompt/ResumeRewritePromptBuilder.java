package com.resumeiq.rewrite.prompt;

import java.util.List;

import org.springframework.stereotype.Component;

import com.resumeiq.rewrite.dto.request.RewriteInstruction;

@Component
public class ResumeRewritePromptBuilder {

	public String buildPrompt(String resumeText, String jobDescriptionText, List<RewriteInstruction> instructions) {

		StringBuilder prompt = new StringBuilder();

		prompt.append("""
				You are an expert resume writer, ATS specialist, and technical recruiter.

				Your task is to rewrite ONLY the requested resume sections.

				Rules:

				1. Rewrite ONLY the requested sections.
				2. Do NOT modify any other sections.
				3. Optimize each section for ATS.
				4. Naturally include relevant keywords from the Job Description.
				5. Do NOT invent fake experience.
				6. Do NOT exaggerate achievements.
				7. Keep the candidate's information truthful.
				8. Improve grammar, readability, and professionalism.
				9. Return ONLY valid JSON.
				10. Do NOT use markdown.
				11. Do NOT wrap the response inside ```.

				Return EXACTLY this JSON structure:

				{
				  "rewrittenSections": [
				    {
				      "section": "",
				      "rewrittenContent": "",
				      "explanation": ""
				    }
				  ]
				}

				Rewrite the following sections:

				""");

		int index = 1;

		for (RewriteInstruction instruction : instructions) {

			prompt.append(index++).append(". Section: ").append(instruction.getSection().name()).append("\n");

			prompt.append("   Goal: ").append(instruction.getGoal()).append("\n\n");
		}

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