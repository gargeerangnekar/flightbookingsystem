package com.capgemini.flightbookingsystem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.services.AirLineAdminService;

@RestController
@RequestMapping("/admins")
public class AirLineAdminController {

    private final AirLineAdminService airLineAdminService;

    @Autowired
    public AirLineAdminController(AirLineAdminService airLineAdminService) {
        this.airLineAdminService = airLineAdminService;
    }

    // GET all admins
    @GetMapping
    public ResponseEntity<List<AirLineAdmin>> getAllAdmins() {
        List<AirLineAdmin> admins = airLineAdminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    // GET admin by ID
    @GetMapping("/{adminId}")
    public ResponseEntity<AirLineAdmin> getAdminById(@PathVariable Integer adminId) {
        AirLineAdmin admin = airLineAdminService.getAdminById(adminId);
        return ResponseEntity.ok(admin);
    }

    // POST new admin
    @PostMapping
    public ResponseEntity<AirLineAdmin> createAdmin(@RequestBody AirLineAdmin admin) {
        AirLineAdmin createdAdmin = airLineAdminService.createAdmin(admin);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    // PUT update admin
    @PutMapping("/{adminId}")
    public ResponseEntity<AirLineAdmin> updateAdmin(
            @PathVariable Integer adminId,
            @RequestBody AirLineAdmin admin) {
        AirLineAdmin updatedAdmin = airLineAdminService.updateAdmin(adminId, admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    // DELETE admin
    @DeleteMapping("/{adminId}")
    public ResponseEntity<Map<String, Boolean>> deleteAdmin(@PathVariable Integer adminId) {
        airLineAdminService.deleteAdmin(adminId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return ResponseEntity.ok(response);
    }

    // Check email existence
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@PathVariable String email) {
        boolean exists = airLineAdminService.existsByAirlineEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    // Check contact number existence
    @GetMapping("/check-contact/{contactNumber}")
    public ResponseEntity<Map<String, Boolean>> checkContactExists(@PathVariable String contactNumber) {
        boolean exists = airLineAdminService.existsByContactNumber(contactNumber);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
}
