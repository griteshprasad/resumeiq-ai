package com.resumeiq.jobdescription.entity;

import com.resumeiq.auth.entity.User;
import com.resumeiq.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job_descriptions")
@Getter
@Setter
@NoArgsConstructor
public class JobDescription extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private String originalFileName;

	@Column(nullable = false, unique = true)
	private String storedFileName;

	@Column(nullable = false)
	private String contentType;

	@Column(nullable = false)
	private Long fileSize;

	@Column(nullable = false)
	private String storagePath;

	@Column(columnDefinition = "TEXT")
	private String extractedText;

}
