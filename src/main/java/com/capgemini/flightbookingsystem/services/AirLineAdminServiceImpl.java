package com.capgemini.flightbookingsystem.services;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.repositories.AirLineAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirLineAdminServiceImpl implements AirLineAdminService {

    @Autowired
    private AirLineAdminRepository airLineAdminRepository;

    @Override
    public List<AirLineAdmin> getAllAirlineAdmins() {
        return airLineAdminRepository.findAll();
    }

    @Override
    public AirLineAdmin getAirlineAdminById(Integer id) {
        return airLineAdminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
    }

    @Override
    public AirLineAdmin createAdmin(AirLineAdmin admin) {
        return airLineAdminRepository.save(admin);
    }

    @Override
    public AirLineAdmin updateAdmin(Integer id, AirLineAdmin admin) {
        AirLineAdmin existingAdmin = getAirlineAdminById(id);
        existingAdmin.setAirlineAdminName(admin.getAirlineAdminName());
        existingAdmin.setPassword(admin.getPassword());
        existingAdmin.setContactNumber(admin.getContactNumber());
        existingAdmin.setAirlineEmail(admin.getAirlineEmail());
        return airLineAdminRepository.save(existingAdmin);
    }

    @Override
    public ResponseEntity<String> deleteAdmin(Integer id) {
        if (airLineAdminRepository.existsById(id)) {
            airLineAdminRepository.deleteById(id);
            return ResponseEntity.ok("Airline admin deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Admin not found");
        }
    }

    @Override
    public boolean existsByAirlineEmail(String email) {
        return airLineAdminRepository.existsByAirlineEmail(email);
    }

    @Override
    public boolean existsByContactNumber(String contactNumber) {
        return airLineAdminRepository.existsByContactNumber(contactNumber);
    }
}
