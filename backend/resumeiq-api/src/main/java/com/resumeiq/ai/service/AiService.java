package com.resumeiq.ai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.resumeiq.ai.provider.AiClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AiService.class);

    private final AiClient aiClient;

    public String generateResponse(String prompt) {

        LOGGER.info("Sending prompt to AI.");

        String response = aiClient.generateResponse(prompt);

        LOGGER.info("Received response from AI.");

        return response;

    }

}