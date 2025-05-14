package com.capgemini.flightbookingsystem.services;


import java.util.List;

import com.capgemini.flightbookingsystem.entities.AirlinesAdmins;

public interface AirlinesAdminsService {
    List<AirlinesAdmins> getAllAdmins();
    AirlinesAdmins getAdminById(Integer id);
    AirlinesAdmins createAdmin(AirlinesAdmins admin);
    AirlinesAdmins updateAdmin(Integer id, AirlinesAdmins admin);
    boolean deleteAdmin(Integer id);
    boolean existsByAirlineEmail(String airlineEmail);
    boolean existsByContactNumber(String contactNumber);
}
