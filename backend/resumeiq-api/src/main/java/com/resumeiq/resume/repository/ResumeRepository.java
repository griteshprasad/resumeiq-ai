package com.resumeiq.resume.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resumeiq.resume.entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, UUID> {
	
}
