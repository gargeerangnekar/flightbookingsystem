package com.capgemini.flightbookingsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.entities.Flights;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
	
	 List<Flights> findByAirportId(Integer airportId);
	  
}
