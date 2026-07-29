package com.resumeiq.resume.service.parser;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfResumeParser implements ResumeParser {

    @Override
    public boolean supports(MultipartFile file) {

        String filename = file.getOriginalFilename();
        return filename != null && filename.toLowerCase().endsWith(".pdf");
    }

    @Override
    public String extractText(MultipartFile file) {

        try (PDDocument document = Loader.loadPDF(file.getBytes())) {

            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);
            
        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to parse PDF.",
                    ex);
        }

    }

}