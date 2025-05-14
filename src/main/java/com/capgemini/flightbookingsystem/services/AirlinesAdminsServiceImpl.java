package com.capgemini.flightbookingsystem.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.AirlinesAdmins;
import com.capgemini.flightbookingsystem.exceptions.ResourceNotFoundException;
import com.capgemini.flightbookingsystem.repositories.AirlinesAdminsRepository;

@Service
public class AirlinesAdminsServiceImpl implements AirlinesAdminsService {

    private final AirlinesAdminsRepository airlinesAdminsRepository;

    @Autowired
    public AirlinesAdminsServiceImpl(AirlinesAdminsRepository airlinesAdminsRepository) {
        this.airlinesAdminsRepository = airlinesAdminsRepository;
    }

    @Override
    public List<AirlinesAdmins> getAllAdmins() {
        return airlinesAdminsRepository.findAll();
    }

    @Override
    public AirlinesAdmins getAdminById(Integer id) {
        Optional<AirlinesAdmins> adminOptional = airlinesAdminsRepository.findById(id);
        if (adminOptional.isPresent()) {
            return adminOptional.get();
        } else {
            throw new ResourceNotFoundException("Admin not found with id: " + id);
        }
    }

    @Override
    public AirlinesAdmins createAdmin(AirlinesAdmins admin) {
        return airlinesAdminsRepository.save(admin);
    }

    @Override
    public AirlinesAdmins updateAdmin(Integer id, AirlinesAdmins adminDetails) {
        Optional<AirlinesAdmins> adminOptional = airlinesAdminsRepository.findById(id);
        if (adminOptional.isPresent()) {
            AirlinesAdmins existingAdmin = adminOptional.get();
            existingAdmin.setAirlineAdminName(adminDetails.getAirlineAdminName());
            existingAdmin.setPassword(adminDetails.getPassword());
            existingAdmin.setContactNumber(adminDetails.getContactNumber());
            existingAdmin.setAirlineEmail(adminDetails.getAirlineEmail());
            return airlinesAdminsRepository.save(existingAdmin);
        } else {
            throw new ResourceNotFoundException("Admin not found with id: " + id);
        }
    }

    @Override
    public boolean deleteAdmin(Integer id) {
        Optional<AirlinesAdmins> adminOptional = airlinesAdminsRepository.findById(id);
        if (adminOptional.isPresent()) {
            airlinesAdminsRepository.delete(adminOptional.get());
            return true;
        } else {
            throw new ResourceNotFoundException("Admin not found with id: " + id);
        }
    }
    
    @Override
    public boolean existsByAirlineEmail(String airlineEmail) {
        return airlinesAdminsRepository.existsByAirlineEmail(airlineEmail);
    }
    
    @Override
    public boolean existsByContactNumber(String contactNumber) {
        return airlinesAdminsRepository.existsByContactNumber(contactNumber);
    }
}
