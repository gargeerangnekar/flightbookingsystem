package com.capgemini.flightbookingsystem.controllers;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.services.AirLineAdminService;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/airline-admin")
@Slf4j
public class AirLineAdminController {

    private final AirLineAdminService airLineAdminService;

    @Autowired
    public AirLineAdminController(AirLineAdminService airLineAdminService) {
        this.airLineAdminService = airLineAdminService;
    }

    // Get all admins
    @GetMapping
    public ResponseEntity<List<AirLineAdmin>> getAllAdmins() {
        log.info("Fetching all airline admins");
        List<AirLineAdmin> admins = airLineAdminService.getAllAirlineAdmins();
        log.debug("Total admins fetched: {}", admins.size());
        return ResponseEntity.ok(admins);
    }

    // Get admin by ID
    @GetMapping("/{adminId}")
    public ResponseEntity<AirLineAdmin> getAdminById(@PathVariable Integer adminId) {
        log.info("Fetching airline admin with ID: {}", adminId);
        AirLineAdmin admin = airLineAdminService.getAirlineAdminById(adminId);
        log.debug("Fetched airline admin details: {}", admin);
        return ResponseEntity.ok(admin);
    }

    // Create new admin
    @PostMapping
    public ResponseEntity<?> createAdmin(@Valid @RequestBody AirLineAdmin admin, BindingResult result) {
        log.info("Creating new airline admin: {}", admin);

        if (result.hasErrors()) {
            log.warn("Validation failed during admin creation: {}", result.getAllErrors());
            Map<String, Object> response = new HashMap<>();
            response.put("errors", result.getAllErrors().stream()
                .map(e -> e.getDefaultMessage())
                .toList());
            return ResponseEntity.badRequest().body(response);
        }

        AirLineAdmin saved = airLineAdminService.createAdmin(admin);
        log.debug("Airline admin created successfully with ID: {}", saved.getAirlineAdminId());

        return ResponseEntity.created(URI.create("/api/airline-admin/" + saved.getAirlineAdminId()))
                .body(saved);
    }

    // Update existing admin
    @PutMapping("/{adminId}")
    public ResponseEntity<?> updateAdmin(@PathVariable Integer adminId,
                                         @Valid @RequestBody AirLineAdmin admin,
                                         BindingResult result) {
        log.info("Updating airline admin with ID: {} using data: {}", adminId, admin);

        if (result.hasErrors()) {
            log.warn("Validation failed for update: {}", result.getAllErrors());
            Map<String, Object> response = new HashMap<>();
            response.put("errors", result.getAllErrors().stream()
                .map(e -> e.getDefaultMessage())
                .toList());
            return ResponseEntity.badRequest().body(response);
        }

        AirLineAdmin updated = airLineAdminService.updateAdmin(adminId, admin);
        log.debug("Airline admin with ID {} updated successfully: {}", adminId, updated);
        return ResponseEntity.ok(updated);
    }

    // Delete admin by ID
    @DeleteMapping("/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer adminId) {
        log.info("Deleting airline admin with ID: {}", adminId);
        airLineAdminService.deleteAdmin(adminId);
        log.debug("Airline admin with ID {} deleted successfully", adminId);
        return ResponseEntity.ok("Airline admin deleted successfully");
    }

    // Check if email exists
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@PathVariable String email) {
        log.info("Checking if email exists: {}", email);
        boolean exists = airLineAdminService.existsByAirlineEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    // Check if contact number exists
    @GetMapping("/check-contact/{contactNumber}")
    public ResponseEntity<Map<String, Boolean>> checkContactExists(@PathVariable String contactNumber) {
        log.info("Checking if contact number exists: {}", contactNumber);
        boolean exists = airLineAdminService.existsByContactNumber(contactNumber);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
}
