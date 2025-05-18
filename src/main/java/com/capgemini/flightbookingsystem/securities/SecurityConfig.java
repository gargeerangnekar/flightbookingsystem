package com.capgemini.flightbookingsystem.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	CustomUserDetailsService customUserDetailsService;
	JwtAuthenticationFilter authenticationFilter;
	JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	public SecurityConfig(CustomUserDetailsService customUserDetailsService,
			JwtAuthenticationFilter authenticationFilter, JwtAuthenticationEntryPoint authenticationEntryPoint) {
		super();
		this.customUserDetailsService = customUserDetailsService;
		this.authenticationFilter = authenticationFilter;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public static AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	        // CSRF protection is disabled because JWT is used for stateless authentication (no session or cookies).
	        .csrf(AbstractHttpConfigurer::disable)
	        .cors(Customizer.withDefaults())
	        .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/auth/signin", "/auth/register", "/auth/register-admin").permitAll()
	            .requestMatchers("/**").hasAnyRole("USER", "ADMIN")
	            .anyRequest().authenticated()
	        )
	        .authenticationProvider(authenticationProvider())
	        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
	        .build();
	}

	
	
}
