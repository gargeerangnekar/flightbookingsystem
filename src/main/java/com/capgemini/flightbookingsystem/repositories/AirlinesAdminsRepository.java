package com.capgemini.flightbookingsystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.flightbookingsystem.entities.AirlinesAdmins;

@Repository
public interface AirlinesAdminsRepository extends JpaRepository<AirlinesAdmins, Integer> {
    boolean existsByAirlineAdminName(String airlineAdminName);
    boolean existsByAirlineEmail(String airlineEmail);
    boolean existsByContactNumber(String contactNumber);
}
