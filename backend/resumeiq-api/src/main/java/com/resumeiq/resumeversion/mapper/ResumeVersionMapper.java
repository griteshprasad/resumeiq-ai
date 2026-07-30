package com.resumeiq.resumeversion.mapper;

import org.mapstruct.Mapper;

import com.resumeiq.resumeversion.dto.response.ResumeVersionResponse;
import com.resumeiq.resumeversion.entity.ResumeVersion;

@Mapper(componentModel = "spring")
public interface ResumeVersionMapper {

    ResumeVersionResponse toResponse(ResumeVersion entity);

}