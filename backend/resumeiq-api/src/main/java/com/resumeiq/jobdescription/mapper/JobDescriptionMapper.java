package com.resumeiq.jobdescription.mapper;

import org.mapstruct.Mapper;

import com.resumeiq.jobdescription.dto.response.JobDescriptionResponse;
import com.resumeiq.jobdescription.entity.JobDescription;

@Mapper(componentModel = "spring")
public interface JobDescriptionMapper {

	JobDescriptionResponse toResponse(JobDescription jobDescription);

}