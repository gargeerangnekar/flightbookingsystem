package com.capgemini.flightbookingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.exceptions.AirlineAdminNotFoundException;
import com.capgemini.flightbookingsystem.repositories.AirLineAdminRepository;

@Service
public class AirLineAdminServiceImpl implements AirLineAdminService {

    private final AirLineAdminRepository airLineAdminRepository;

    @Autowired
    public AirLineAdminServiceImpl(AirLineAdminRepository airLineAdminRepository) {
        this.airLineAdminRepository = airLineAdminRepository;
    }

    @Override
    public List<AirLineAdmin> getAllAdmins() {
        return airLineAdminRepository.findAll();
    }

    @Override
    public AirLineAdmin getAdminById(Integer id) {
        return airLineAdminRepository.findById(id)
                .orElseThrow(() -> new AirlineAdminNotFoundException("Admin not found with ID: " + id));
    }

    @Override
    public AirLineAdmin createAdmin(AirLineAdmin admin) {
        return airLineAdminRepository.save(admin);
    }

    @Override
    public AirLineAdmin updateAdmin(Integer id, AirLineAdmin adminDetails) {
        AirLineAdmin existingAdmin = airLineAdminRepository.findById(id)
                .orElseThrow(() -> new AirlineAdminNotFoundException("Admin not found with ID: " + id));

        existingAdmin.setAirlineAdminName(adminDetails.getAirlineAdminName());
        existingAdmin.setPassword(adminDetails.getPassword());
        existingAdmin.setContactNumber(adminDetails.getContactNumber());
        existingAdmin.setAirlineEmail(adminDetails.getAirlineEmail());

        return airLineAdminRepository.save(existingAdmin);
    }

    @Override
    public boolean deleteAdmin(Integer id) {
        AirLineAdmin existingAdmin = airLineAdminRepository.findById(id)
                .orElseThrow(() -> new AirlineAdminNotFoundException("Admin not found with ID: " + id));

        airLineAdminRepository.delete(existingAdmin);
        return true;
    }

    @Override
    public boolean existsByAirlineEmail(String airlineEmail) {
        return airLineAdminRepository.existsByAirlineEmail(airlineEmail);
    }

    @Override
    public boolean existsByContactNumber(String contactNumber) {
        return airLineAdminRepository.existsByContactNumber(contactNumber);
    }
}
