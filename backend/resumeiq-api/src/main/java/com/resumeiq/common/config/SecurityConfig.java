package com.resumeiq.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.resumeiq.auth.security.JwtAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	http
    		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
    		.csrf(AbstractHttpConfigurer::disable)
        	.httpBasic(AbstractHttpConfigurer::disable)
        	.formLogin(AbstractHttpConfigurer::disable)
        	.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        	.authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/api/v1/auth/**",
                        "/api/v1/ai/**",
                        "/api/v1/health",
                        "/actuator/health",
                        
                        "/swagger-ui/**",
        		        "/swagger-ui.html",
        		        "/v3/api-docs/**",
        		        "/api-docs/**")
                .permitAll()
                .anyRequest()
                .authenticated())
        	.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}