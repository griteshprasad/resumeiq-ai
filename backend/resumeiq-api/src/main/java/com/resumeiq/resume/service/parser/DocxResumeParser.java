package com.resumeiq.resume.service.parser;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocxResumeParser implements ResumeParser {

    @Override
    public boolean supports(MultipartFile file) {

        return file.getOriginalFilename()
                .toLowerCase()
                .endsWith(".docx");

    }

    @Override
    public String extractText(MultipartFile file) {

        return "";

    }

}
