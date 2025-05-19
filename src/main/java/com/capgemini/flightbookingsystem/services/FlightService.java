package com.capgemini.flightbookingsystem.services;

import java.util.List;

import com.capgemini.flightbookingsystem.dto.AirportFetchingDto;
import com.capgemini.flightbookingsystem.dto.CityWithAirportIdDto;
import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.entities.Flights;

public interface FlightService {

	List<Flights> getAllFlights();

	Flights getFlightById(Integer flightId);

	Flights createNewFlight(Flights flight);

	Flights updateFlightById(Integer flightId, Flights flight);
	
	Flights patchFlightById(Integer flightId, Flights flight);

	void deleteFlight(Integer flightId);

	List<Flights> sortFlightsByNumber(String direction);

	List<Flights> sortFlightsByAmount(String direction);

	AirportFetchingDto getFlightDTO(Integer flightId);
	
	List<CityWithAirportIdDto> getArrivalCities();
	
	List<String> getAircraftModels();
	
}
