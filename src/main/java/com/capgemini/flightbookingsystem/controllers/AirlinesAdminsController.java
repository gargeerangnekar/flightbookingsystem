package com.capgemini.flightbookingsystem.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.flightbookingsystem.entities.AirlinesAdmins;
import com.capgemini.flightbookingsystem.services.AirlinesAdminsService;

@RestController
@RequestMapping()
public class AirlinesAdminsController {

    private final AirlinesAdminsService airlinesAdminsService;

    @Autowired
    public AirlinesAdminsController(AirlinesAdminsService airlinesAdminsService) {
        this.airlinesAdminsService = airlinesAdminsService;
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AirlinesAdmins>> getAllAdmins() {
        List<AirlinesAdmins> admins = airlinesAdminsService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/admins/{adminId}")
    public ResponseEntity<AirlinesAdmins> getAdminById(@PathVariable("adminId") Integer adminId) {
        AirlinesAdmins admin = airlinesAdminsService.getAdminById(adminId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping("/admins")
    public ResponseEntity<AirlinesAdmins> createAdmin(@RequestBody AirlinesAdmins admin) {
        AirlinesAdmins createdAdmin = airlinesAdminsService.createAdmin(admin);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/admins/{adminId}")
    public ResponseEntity<AirlinesAdmins> updateAdmin(
            @PathVariable("adminId") Integer adminId,
            @RequestBody AirlinesAdmins admin) {
        AirlinesAdmins updatedAdmin = airlinesAdminsService.updateAdmin(adminId, admin);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    @DeleteMapping("/admins/{adminId}")
    public ResponseEntity<Map<String, Boolean>> deleteAdmin(@PathVariable("adminId") Integer adminId) {
        airlinesAdminsService.deleteAdmin(adminId);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/admins/check-email/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@PathVariable String email) {
        boolean exists = airlinesAdminsService.existsByAirlineEmail(email);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/admins/check-contact/{contactNumber}")
    public ResponseEntity<Map<String, Boolean>> checkContactExists(@PathVariable String contactNumber) {
        boolean exists = airlinesAdminsService.existsByContactNumber(contactNumber);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

