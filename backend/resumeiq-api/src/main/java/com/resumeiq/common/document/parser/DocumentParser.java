package com.resumeiq.common.document.parser;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentParser {

    boolean supports(MultipartFile file);

    String extractText(MultipartFile file);

}
