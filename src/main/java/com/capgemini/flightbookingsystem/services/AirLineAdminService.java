package com.capgemini.flightbookingsystem.services;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import org.springframework.http.ResponseEntity;

import java.util.List;
//18
public interface AirLineAdminService {
    List<AirLineAdmin> getAllAirlineAdmins();
    AirLineAdmin getAirlineAdminById(Integer id);
    AirLineAdmin createAdmin(AirLineAdmin admin);
    AirLineAdmin updateAdmin(Integer id, AirLineAdmin admin);
    ResponseEntity<String> deleteAdmin(Integer id);
    boolean existsByAirlineEmail(String email);
    boolean existsByContactNumber(String contactNumber);
}
