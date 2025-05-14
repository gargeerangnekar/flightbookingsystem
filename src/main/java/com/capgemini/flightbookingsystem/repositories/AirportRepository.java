package com.capgemini.flightbookingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.flightbookingsystem.entities.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
	// Custom queries (if needed)
	Airport findByAirportName(String airportName);
}
