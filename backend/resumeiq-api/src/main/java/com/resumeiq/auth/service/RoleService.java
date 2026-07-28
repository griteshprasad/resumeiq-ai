package com.resumeiq.auth.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resumeiq.auth.entity.Role;
import com.resumeiq.auth.repository.RoleRepository;
import com.resumeiq.common.enums.RoleType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public void initializeDefaultRoles() {

        Set<RoleType> existingRoles = roleRepository.findAll()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        List<Role> rolesToCreate = Arrays.stream(RoleType.values())
                .filter(roleType -> !existingRoles.contains(roleType))
                .map(roleType -> {
                    Role role = new Role();
                    role.setName(roleType);
                    return role;
                })
                .toList();

        if (!rolesToCreate.isEmpty()) {
            roleRepository.saveAll(rolesToCreate);
        }
    }
}