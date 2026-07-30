package com.resumeiq.ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resumeiq.ai.dto.request.ChatRequest;
import com.resumeiq.ai.dto.response.ChatResponse;
import com.resumeiq.ai.service.AiService;
import com.resumeiq.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class AiController {

	private final AiService aiService;

    @PostMapping("/chat")
    public ResponseEntity<ApiResponse<ChatResponse>> chat( @Validated @RequestBody ChatRequest request) {

        String answer = aiService.chat(request.getPrompt());

        ChatResponse response = new ChatResponse(answer);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Response generated successfully.",
                        response));
    }

}