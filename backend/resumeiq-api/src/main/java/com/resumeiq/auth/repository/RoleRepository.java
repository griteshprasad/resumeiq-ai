package com.resumeiq.auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resumeiq.auth.entity.Role;
import com.resumeiq.common.enums.RoleType;

public interface RoleRepository extends JpaRepository<Role, UUID> {

	Optional<Role> findByName(RoleType name);

}