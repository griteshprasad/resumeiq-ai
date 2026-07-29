package com.resumeiq.resume.service.parser;

import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocxResumeParser implements ResumeParser {

    @Override
    public boolean supports(MultipartFile file) {

        String filename = file.getOriginalFilename();

        return filename != null && filename.toLowerCase().endsWith(".docx");
    }

    @Override
    public String extractText(MultipartFile file) {

        try (
                XWPFDocument document = new XWPFDocument(file.getInputStream());
                XWPFWordExtractor extractor = new XWPFWordExtractor(document)) 
        {
            return extractor.getText();
        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to parse DOCX.",
                    ex);
        }

    }

}