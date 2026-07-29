package com.resumeiq.resume.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeResponse {

    private UUID id;

    private String originalFileName;

    private Long fileSize;

    private LocalDateTime createdAt;

}
