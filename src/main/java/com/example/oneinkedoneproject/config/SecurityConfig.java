package com.example.oneinkedoneproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder encode(){
		return new BCryptPasswordEncoder();
	}
}