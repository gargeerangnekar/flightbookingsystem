package com.capgemini.flightbookingsystem.services;

import java.util.List;

import com.capgemini.flightbookingsystem.entities.Flights;

public interface FlightService {

	List<Flights> getAllFlights();
	
	Flights getFlightById(Long flightId);
	
	Flights createNewFlight(Flights flight);
	
	Flights updateFlightById(Long flightId, Flights flight);
	
	void deleteFlight(Long flightId);
}
