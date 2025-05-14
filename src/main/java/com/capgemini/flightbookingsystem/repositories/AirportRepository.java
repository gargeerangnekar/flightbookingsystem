package com.capgemini.flightbookingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.flightbookingsystem.entities.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
	Airport findByAirportName(String airportName);
}
