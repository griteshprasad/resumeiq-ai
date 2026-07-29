package com.resumeiq.jobdescription.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resumeiq.auth.entity.User;
import com.resumeiq.jobdescription.entity.JobDescription;

@Repository
public interface JobDescriptionRepository extends JpaRepository<JobDescription, UUID> {
	List<JobDescription> findAllByUserOrderByCreatedAtDesc(User user);
	Optional<JobDescription> findByIdAndUser(UUID id, User user);
}
