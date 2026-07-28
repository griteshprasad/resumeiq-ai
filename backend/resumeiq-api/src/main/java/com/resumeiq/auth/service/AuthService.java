package com.resumeiq.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.resumeiq.auth.dto.request.RegisterRequest;
import com.resumeiq.auth.dto.response.RegisterResponse;
import com.resumeiq.auth.entity.Role;
import com.resumeiq.auth.entity.User;
import com.resumeiq.auth.mapper.UserMapper;
import com.resumeiq.auth.repository.RoleRepository;
import com.resumeiq.auth.repository.UserRepository;
import com.resumeiq.common.enums.RoleType;
import com.resumeiq.common.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        User user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role userRole = roleRepository.findByName(RoleType.USER)
                .orElseThrow(() ->
                        new ResourceNotFoundException("USER role not found."));

        user.addRole(userRole);

        User savedUser = userRepository.save(user);

        return userMapper.toRegisterResponse(savedUser);
    }

}