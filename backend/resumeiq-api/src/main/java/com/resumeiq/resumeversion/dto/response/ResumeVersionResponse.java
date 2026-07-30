package com.resumeiq.resumeversion.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ResumeVersionResponse {

	private UUID id;

	private Integer versionNumber;

	private String title;

	private String contentJson;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}