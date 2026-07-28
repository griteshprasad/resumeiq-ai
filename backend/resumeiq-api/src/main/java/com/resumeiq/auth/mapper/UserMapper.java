package com.resumeiq.auth.mapper;

import org.mapstruct.Mapper;

import com.resumeiq.auth.dto.request.RegisterRequest;
import com.resumeiq.auth.dto.response.RegisterResponse;
import com.resumeiq.auth.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest request);
    RegisterResponse toRegisterResponse(User user);

}