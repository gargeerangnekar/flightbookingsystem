package com.capgemini.flightbookingsystem.services;

import java.util.List;

import com.capgemini.flightbookingsystem.dto.AirportFetchingDto;
import com.capgemini.flightbookingsystem.entities.Flights;

//17
//service interface
public interface FlightService {

	List<Flights> getAllFlights();

	Flights getFlightById(Integer flightId);

	Flights createNewFlight(Flights flight);

	Flights updateFlightById(Integer flightId, Flights flight);

	void deleteFlight(Integer flightId);

	List<Flights> sortFlightsByNumber(String direction);

	List<Flights> sortFlightsByAmount(String direction);

	AirportFetchingDto getFlightDTO(Integer flightId);
}
