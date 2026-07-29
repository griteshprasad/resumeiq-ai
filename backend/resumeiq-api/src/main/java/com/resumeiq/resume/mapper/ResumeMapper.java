package com.resumeiq.resume.mapper;

import org.mapstruct.Mapper;

import com.resumeiq.resume.dto.response.ResumeResponse;
import com.resumeiq.resume.entity.Resume;

@Mapper(componentModel = "spring")
public interface ResumeMapper {

    ResumeResponse toResponse(Resume resume);

}