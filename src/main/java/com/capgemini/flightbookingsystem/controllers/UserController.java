package com.capgemini.flightbookingsystem.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.flightbookingsystem.entities.User;
import com.capgemini.flightbookingsystem.services.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		log.info("Fetching all users");
		List<User> users = userService.getAllUsers();
		log.debug("Total users fetched: {}", users.size());
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		log.info("Fetching user with ID: {}", id);
		User user = userService.getUserById(id);
		log.debug("Fetched user details: {}", user);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody User enrollment, BindingResult result) {
		log.info("Creating new user with data: {}", enrollment);

		if (result.hasErrors()) {
			log.warn("Validation failed: {}", result.getAllErrors());
			throw new IllegalArgumentException("Invalid Data");
		}

		User saved = userService.createUser(enrollment);
		log.debug("User created successfully with ID: {}", saved.getUserId());

		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/users/" + saved.getUserId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody User newUser, BindingResult result) {
		log.info("Updating user with ID: {} using data: {}", id, newUser);
		
		if (result.hasErrors()) {
			log.warn("Validation failed for update: {}", result.getAllErrors());
			throw new IllegalArgumentException("Invalid Data");
		}

		User updated = userService.putUser(id, newUser);
		log.debug("User with ID {} updated successfully to: {}", id, updated);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}


	@PatchMapping("/{id}")
	public ResponseEntity<?> patchUser(@PathVariable Integer id, @Valid @RequestBody User patch, BindingResult result) {
	    if (result.hasErrors()) {
	    	log.warn("Validation failed for patch: {}", result.getAllErrors());
			throw new IllegalArgumentException("Invalid Data");
	    }

	    log.info("Patching user with ID: {} using data: {}", id, patch);
	    User updated = userService.patchUser(id, patch);
	    log.debug("User with ID {} patched successfully to: {}", id, updated);
	    return ResponseEntity.status(HttpStatus.OK).body(updated);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		log.info("Deleting user with ID: {}", id);
		userService.deleteUser(id);
		log.debug("User with ID {} deleted successfully", id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
}
