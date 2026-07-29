package com.resumeiq.common.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.resumeiq.auth.service.RoleService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

	 private final RoleService roleService;

    @Bean
    CommandLineRunner initializeRoles() {

    	return args -> roleService.initializeDefaultRoles();

    }

}
