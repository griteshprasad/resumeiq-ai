package com.resumeiq.ai.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resumeiq.ai.provider.AiClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiService {

	private final AiClient aiClient;

	private final ObjectMapper objectMapper;

	public String generateResponse(String prompt) {
		return aiClient.generateResponse(prompt);
	}

	public <T> T generateResponse(String prompt, Class<T> responseType) {

		try {

			String response = aiClient.generateResponse(prompt);
			response = cleanJson(response);
			
			return objectMapper.readValue(response, responseType);
		} catch (Exception ex) {
			throw new RuntimeException("Unable to parse AI response.", ex);
		}

	}

	private String cleanJson(String response) {
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