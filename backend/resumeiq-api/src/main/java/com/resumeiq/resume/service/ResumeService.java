package com.resumeiq.resume.service;

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

        // Will be populated in the next milestone
        resume.setExtractedText("");

        Resume savedResume = resumeRepository.save(resume);

        return resumeMapper.toResponse(savedResume);

    }

}