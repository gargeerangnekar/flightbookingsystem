package com.capgemini.flightbookingsystem.services;

import java.util.List;

import com.capgemini.flightbookingsystem.entities.*;

public interface AirLineAdminService {
    List<AirLineAdmin> getAllAdmins();
    AirLineAdmin getAdminById(Integer id);
    AirLineAdmin createAdmin(AirLineAdmin admin);
    AirLineAdmin updateAdmin(Integer id, AirLineAdmin admin);
    boolean deleteAdmin(Integer id);
    boolean existsByAirlineEmail(String airlineEmail);
    boolean existsByContactNumber(String contactNumber);
}
