package com.resumeiq.ai.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resumeiq.ai.exception.AiPromptException;
import com.resumeiq.ai.exception.AiProviderException;
import com.resumeiq.ai.exception.AiResponseParsingException;
import com.resumeiq.ai.provider.AiClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiService {

	private final AiClient aiClient;

	private final ObjectMapper objectMapper;

	public String generateResponse(String prompt) {

		if (prompt == null || prompt.isBlank()) {
			throw new AiPromptException("Prompt cannot be empty.");
		}

		try {

			return aiClient.generateResponse(prompt);

		} catch (Exception ex) {
			throw new AiProviderException("Failed to communicate with AI provider.", ex);
		}
	}

	public <T> T generateResponse(String prompt, Class<T> responseType) {

		String response = generateResponse(prompt);
		try {

			response = cleanJson(response);

			return objectMapper.readValue(response, responseType);

		} catch (Exception ex) {
			throw new AiResponseParsingException("Unable to parse AI response.", ex);
		}
	}

	private String cleanJson(String response) {

		if (response == null || response.isBlank()) {
			throw new AiProviderException("AI returned an empty response.");
		}

		response = response.trim();

		if (response.startsWith("```json")) {
			response = response.substring(7);
		}

		if (response.startsWith("```")) {
			response = response.substring(3);
		}

		if (response.endsWith("```")) {
			response = response.substring(0, response.length() - 3);
		}

		return response.trim();
	}

}