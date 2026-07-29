package com.resumeiq.resume.service.parser;

import org.springframework.web.multipart.MultipartFile;

public interface ResumeParserService {

    String extractText(MultipartFile file);

}
