package com.resumeiq.resume.service.parser;

import org.springframework.web.multipart.MultipartFile;

public interface ResumeParser {

    boolean supports(MultipartFile file);

    String extractText(MultipartFile file);

}
