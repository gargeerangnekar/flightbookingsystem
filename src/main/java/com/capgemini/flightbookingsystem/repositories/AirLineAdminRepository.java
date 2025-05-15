package com.capgemini.flightbookingsystem.repositories;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AirLineAdminRepository extends JpaRepository<AirLineAdmin, Integer> {
	boolean existsByAirlineAdminName(String airlineAdminName);

	boolean existsByAirlineEmail(String airlineEmail);

	boolean existsByContactNumber(String contactNumber);

	Optional<AirLineAdmin> findByAirlineEmail(String email);
}
