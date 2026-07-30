package com.resumeiq.jobdescription.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.resumeiq.auth.entity.User;
import com.resumeiq.auth.repository.UserRepository;
import com.resumeiq.common.document.parser.DocumentParser;
import com.resumeiq.common.document.parser.DocumentParserFactory;
import com.resumeiq.common.document.storage.StorageService;
import com.resumeiq.common.document.storage.StoredFile;
import com.resumeiq.common.exception.ResourceNotFoundException;
import com.resumeiq.jobdescription.dto.response.JobDescriptionResponse;
import com.resumeiq.jobdescription.entity.JobDescription;
import com.resumeiq.jobdescription.mapper.JobDescriptionMapper;
import com.resumeiq.jobdescription.repository.JobDescriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class JobDescriptionService {

    private final JobDescriptionRepository jobDescriptionRepository;
    private final UserRepository userRepository;
    private final JobDescriptionMapper jobDescriptionMapper;
    private final StorageService storageService;
    private final DocumentParserFactory parserFactory;

    public JobDescriptionResponse upload(MultipartFile file, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        StoredFile storedFile = storageService.store(file);

        JobDescription jobDescription = new JobDescription();

        jobDescription.setUser(user);
        jobDescription.setOriginalFileName(file.getOriginalFilename());
        jobDescription.setStoredFileName(storedFile.getStoredFileName());
        jobDescription.setStoragePath(storedFile.getStoragePath());
        jobDescription.setContentType(file.getContentType());
        jobDescription.setFileSize(file.getSize());

        DocumentParser parser = parserFactory.getParser(file);
        String extractedText = parser.extractText(file);

        jobDescription.setExtractedText(extractedText);

        JobDescription savedJobDescription = jobDescriptionRepository.save(jobDescription);

        return jobDescriptionMapper.toResponse(savedJobDescription);
    }
    
    public List<JobDescriptionResponse> getAll(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        return jobDescriptionRepository.findAllByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(jobDescriptionMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    public JobDescriptionResponse getById(UUID id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        JobDescription jobDescription = jobDescriptionRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("JobDescription not found."));

        return jobDescriptionMapper.toResponse(jobDescription);
    }
    
    public void delete(UUID id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        JobDescription jobDescription = jobDescriptionRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("JobDescription not found."));

        storageService.delete(jobDescription.getStoragePath());

        jobDescriptionRepository.delete(jobDescription);

    }
    
    public JobDescription getEntityById(UUID id, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        return jobDescriptionRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resume not found."));
    }

}