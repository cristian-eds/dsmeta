package com.cristian.eds.dsmeta.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http
		 	.csrf(csrf -> csrf.disable()) // Avalie a necessidade de desabilitar o CSRF
		 	.headers(headers -> headers.frameOptions(frame -> frame.disable()))
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
	        .cors(cors -> cors.configurationSource(request -> {
	        	CorsConfiguration corsConfig = new CorsConfiguration();
	        	corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
	        	corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	        	corsConfig.addAllowedHeader("*");
	            return corsConfig;
	        }));

		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}