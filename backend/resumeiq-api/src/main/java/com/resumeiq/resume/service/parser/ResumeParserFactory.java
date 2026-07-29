package com.resumeiq.resume.service.parser;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeParserFactory {

    private final List<ResumeParser> parsers;

    public ResumeParser getParser(MultipartFile file) {

        return parsers.stream()
                .filter(parser -> parser.supports(file))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Unsupported file type"));
    }

}
