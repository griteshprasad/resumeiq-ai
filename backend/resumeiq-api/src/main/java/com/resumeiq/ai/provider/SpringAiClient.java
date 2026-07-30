package com.resumeiq.ai.provider;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringAiClient implements AiClient {

	@Qualifier("aiChatClient")
	private final ChatClient chatClient;

    @Override
    public String generateResponse(String prompt) {

        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();

    }

}