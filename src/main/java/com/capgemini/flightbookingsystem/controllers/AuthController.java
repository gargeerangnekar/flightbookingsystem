package com.capgemini.flightbookingsystem.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.flightbookingsystem.dto.LoginDto;
import com.capgemini.flightbookingsystem.dto.ResponseToken;
import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.entities.User;
import com.capgemini.flightbookingsystem.exceptions.EmailAlreadyExist;
import com.capgemini.flightbookingsystem.repositories.AirLineAdminRepository;
import com.capgemini.flightbookingsystem.repositories.UserRepository;
import com.capgemini.flightbookingsystem.securities.JwtUtils;
import com.capgemini.flightbookingsystem.services.AirLineAdminService;
import com.capgemini.flightbookingsystem.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	AuthenticationManager authenticationManager;
	UserService userService;
	PasswordEncoder passwordEncoder;
	JwtUtils jwtUtils;
	UserRepository userRepository;
	AirLineAdminRepository airLineAdminRepository;
	AirLineAdminService adminService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, UserService userService,
			PasswordEncoder passwordEncoder, JwtUtils jwtUtils, UserRepository userRepository,
			AirLineAdminRepository adminRepository, AirLineAdminService adminService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtils = jwtUtils;
		this.userRepository = userRepository;
		this.adminService = adminService;
		this.airLineAdminRepository = adminRepository;
	}

	@PostMapping("/signin")
	public ResponseEntity<Object> authenticateUser(@RequestBody LoginDto loginDto) {
	    Authentication authentication = authenticationManager
	            .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

	    if (!authentication.isAuthenticated()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }

	    Map<String, Object> claims = new HashMap<>();

	    Optional<User> optionalUser = userRepository.findByEmail(loginDto.getUsername());
	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        claims.put("email", user.getEmail());
	        claims.put("phone", user.getPhoneNumber());
	        claims.put("name", user.getName());
	        claims.put("userId", user.getUserId());
	        claims.put("userType", "USER");
	        claims.put("passPort", user.getPassportNumber());
	    } else {
	        Optional<AirLineAdmin> optionalAdmin = airLineAdminRepository.findByAirlineEmail(loginDto.getUsername());
	        if (optionalAdmin.isPresent()) {
	            AirLineAdmin admin = optionalAdmin.get();
	            claims.put("email", admin.getAirlineEmail());
	            claims.put("name", admin.getAirlineAdminName());
	            claims.put("userId", admin.getAirlineAdminId());
	            claims.put("userType", "ADMIN");
	            claims.put("phone", admin.getContactNumber());
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	        }
	    }

	    String token = jwtUtils.generateToken(loginDto.getUsername(), claims);
	    ResponseToken responseToken = new ResponseToken(token);
	    return ResponseEntity.status(HttpStatus.OK).body(responseToken);
	}


	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		if (userService.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyExist("User already registered");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}

	@PostMapping("/register-admin")
	public ResponseEntity<AirLineAdmin> registerAdmin(@RequestBody AirLineAdmin admin) {
		if (adminService.existsByAirlineEmail(admin.getAirlineEmail())) {
			throw new EmailAlreadyExist("Admin already registered");
		}
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(admin));
	}
}
