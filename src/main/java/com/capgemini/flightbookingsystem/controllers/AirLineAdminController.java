package com.capgemini.flightbookingsystem.controllers;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.services.AirLineAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/airline-admin")
public class AirLineAdminController {

    private final AirLineAdminService airLineAdminService;

    @Autowired
    public AirLineAdminController(AirLineAdminService airLineAdminService) {
        this.airLineAdminService = airLineAdminService;
    }

    @GetMapping
    public ResponseEntity<List<AirLineAdmin>> getAllAdmins() {
        return ResponseEntity.ok(airLineAdminService.getAllAirlineAdmins());
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<AirLineAdmin> getAdminById(@PathVariable Integer adminId) {
        return ResponseEntity.ok(airLineAdminService.getAirlineAdminById(adminId));
    }

    @PostMapping
    public ResponseEntity<AirLineAdmin> createAdmin(@RequestBody AirLineAdmin admin) {
        return new ResponseEntity<>(airLineAdminService.createAdmin(admin), HttpStatus.CREATED);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<AirLineAdmin> updateAdmin(
            @PathVariable Integer adminId,
            @RequestBody AirLineAdmin admin) {
        return ResponseEntity.ok(airLineAdminService.updateAdmin(adminId, admin));
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer adminId) {
        airLineAdminService.deleteAdmin(adminId);
        return ResponseEntity.noContent().build(); 
    }
    
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@PathVariable String email) {
        boolean exists = airLineAdminService.existsByAirlineEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-contact/{contactNumber}")
    public ResponseEntity<Map<String, Boolean>> checkContactExists(@PathVariable String contactNumber) {
        boolean exists = airLineAdminService.existsByContactNumber(contactNumber);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
}
