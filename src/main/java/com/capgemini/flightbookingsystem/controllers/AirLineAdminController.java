package com.capgemini.flightbookingsystem.controllers;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.services.AirLineAdminService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/airline-admin")
@Slf4j
public class AirLineAdminController {

	private final AirLineAdminService airLineAdminService;

	@Autowired
	public AirLineAdminController(AirLineAdminService airLineAdminService) {
		this.airLineAdminService = airLineAdminService;
	}

	@GetMapping
	public ResponseEntity<List<AirLineAdmin>> getAllAdmins() {
		log.info("Request received to fetch all airline admins");
		List<AirLineAdmin> admins = airLineAdminService.getAllAirlineAdmins();
		log.debug("Returning {} airline admins", admins.size());
		return ResponseEntity.ok(admins);
	}

	@GetMapping("/{adminId}")
	public ResponseEntity<AirLineAdmin> getAdminById(@PathVariable("adminId") Integer adminId) {
		log.info("Request received to fetch airline admin with ID: {}", adminId);
		AirLineAdmin admin = airLineAdminService.getAirlineAdminById(adminId);
		log.debug("Airline admin fetched: {}", admin);
		return ResponseEntity.ok(admin);
	}

	@PostMapping
	public ResponseEntity<Object> createAdmin(@Valid @RequestBody AirLineAdmin admin, BindingResult result) {
		log.info("Request received to create airline admin: {}", admin);

		if (result.hasErrors()) {
			log.warn("Validation failed: {}", result.getAllErrors());
			List<String> errors = result.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList(); 
			return ResponseEntity.badRequest().body(errors);
		}

		AirLineAdmin saved = airLineAdminService.createAdmin(admin);
		log.debug("Airline admin created with ID: {}", saved.getAirlineAdminId());

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(saved.getAirlineAdminId())
				.toUri();

		return ResponseEntity.created(location).body(saved);
	}

	@PutMapping("/{adminId}")
	public ResponseEntity<Object> updateAdmin(@PathVariable("adminId") Integer adminId,
			@Valid @RequestBody AirLineAdmin admin, BindingResult result) {
		log.info("Request received to update airline admin with ID: {}", adminId);

		if (result.hasErrors()) {
			log.warn("Validation errors for update: {}", result.getAllErrors());
			List<String> errors = result.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList(); // Changed here
			return ResponseEntity.badRequest().body(errors);
		}
		AirLineAdmin updated = airLineAdminService.updateAdmin(adminId, admin);
		log.debug("Airline admin updated for ID {}: {}", adminId, updated);
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{adminId}")
	public ResponseEntity<AirLineAdmin> patchAdmin(@PathVariable("adminId") Integer adminId,
	                                              @RequestBody Map<String, Object> updates) {
	    log.info("Patching airline admin with ID: {} using updates: {}", adminId, updates);

	    AirLineAdmin updated = airLineAdminService.patchAdmin(adminId, updates);
	    log.debug("Airline admin with ID {} patched successfully: {}", adminId, updated);
	    return ResponseEntity.ok(updated);
	}


	@DeleteMapping("/{adminId}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable("adminId") Integer adminId) {
		log.info("Request received to delete airline admin with ID: {}", adminId);
		airLineAdminService.deleteAdmin(adminId);
		log.info("Airline admin with ID {} deleted successfully", adminId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/check-email/{email}")
	public ResponseEntity<Map<String, Boolean>> checkEmailExists(@PathVariable("email") String email) {
		log.info("Checking existence of email: {}", email);
		boolean exists = airLineAdminService.existsByAirlineEmail(email);
		Map<String, Boolean> response = new HashMap<>();
		response.put("exists", exists);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/check-contact/{contactNumber}")
	public ResponseEntity<Map<String, Boolean>> checkContactExists(@PathVariable("contactNumber") String contactNumber) {
		log.info("Checking existence of contact number: {}", contactNumber);
		boolean exists = airLineAdminService.existsByContactNumber(contactNumber);
		Map<String, Boolean> response = new HashMap<>();
		response.put("exists", exists);
		return ResponseEntity.ok(response);
	}
}
