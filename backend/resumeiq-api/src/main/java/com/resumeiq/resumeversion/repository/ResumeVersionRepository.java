package com.resumeiq.resumeversion.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resumeiq.resume.entity.Resume;
import com.resumeiq.resumeversion.entity.ResumeVersion;

public interface ResumeVersionRepository extends JpaRepository<ResumeVersion, UUID> {

	List<ResumeVersion> findByResumeOrderByVersionNumberDesc(Resume resume);

	Optional<ResumeVersion> findTopByResumeOrderByVersionNumberDesc(Resume resume);

}