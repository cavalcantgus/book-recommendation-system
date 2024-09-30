package com.cavalcantgus.book_recommendation_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http

				// Make the endpoint public and authorize all requests to it
				.authorizeHttpRequests((authz) -> authz.requestMatchers("/**").permitAll())

				// Temporarily disabling CSRF
				.csrf((csrf) -> csrf.disable()

				);

		return http.build();
	}
}
