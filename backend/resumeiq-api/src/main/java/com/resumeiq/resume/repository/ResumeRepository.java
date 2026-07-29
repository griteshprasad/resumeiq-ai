package com.resumeiq.resume.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resumeiq.auth.entity.User;
import com.resumeiq.resume.entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, UUID> {
	List<Resume> findAllByUserOrderByCreatedAtDesc(User user);
    Optional<Resume> findByIdAndUser(UUID id, User user);
}
