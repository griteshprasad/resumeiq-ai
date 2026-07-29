package com.resumeiq.user.service;

import org.springframework.stereotype.Service;

import com.resumeiq.auth.entity.User;
import com.resumeiq.auth.repository.UserRepository;
import com.resumeiq.common.exception.ResourceNotFoundException;
import com.resumeiq.user.dto.response.UserProfileResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponse getCurrentUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->  new ResourceNotFoundException("User not found"));

        return new UserProfileResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());

    }

}
