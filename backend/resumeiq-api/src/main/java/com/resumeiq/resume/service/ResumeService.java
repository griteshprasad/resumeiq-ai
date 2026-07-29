package com.resumeiq.resume.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.resumeiq.auth.entity.User;
import com.resumeiq.auth.repository.UserRepository;
import com.resumeiq.common.exception.ResourceNotFoundException;
import com.resumeiq.resume.dto.response.ResumeResponse;
import com.resumeiq.resume.entity.Resume;
import com.resumeiq.resume.mapper.ResumeMapper;
import com.resumeiq.resume.repository.ResumeRepository;
import com.resumeiq.resume.service.parser.ResumeParser;
import com.resumeiq.resume.service.parser.ResumeParserFactory;
import com.resumeiq.resume.service.storage.StorageService;
import com.resumeiq.resume.service.storage.StoredFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final ResumeMapper resumeMapper;
    private final StorageService storageService;
    private final ResumeParserFactory parserFactory;

    public ResumeResponse upload(MultipartFile file, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        StoredFile storedFile = storageService.store(file);

        Resume resume = new Resume();

        resume.setUser(user);
        resume.setOriginalFileName(file.getOriginalFilename());
        resume.setStoredFileName(storedFile.getStoredFileName());
        resume.setStoragePath(storedFile.getStoragePath());
        resume.setContentType(file.getContentType());
        resume.setFileSize(file.getSize());

        ResumeParser parser = parserFactory.getParser(file);
        String extractedText = parser.extractText(file);

        resume.setExtractedText(extractedText);

        Resume savedResume = resumeRepository.save(resume);

        return resumeMapper.toResponse(savedResume);
    }
    
    public List<ResumeResponse> getAll(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        return resumeRepository.findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(resumeMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    public ResumeResponse getById(UUID id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        Resume resume = resumeRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resume not found."));

        return resumeMapper.toResponse(resume);
    }
    
    public void delete(UUID id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        Resume resume = resumeRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resume not found."));

        storageService.delete(resume.getStoragePath());

        resumeRepository.delete(resume);

    }

}