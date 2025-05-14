package com.capgemini.flightbookingsystem.services;


	import java.util.List;

import com.capgemini.flightbookingsystem.entities.Airport;

	public interface AirportService {
	    Airport saveAirport(Airport airport);
	    Airport getAirportById(Long airportId);
	    List<Airport> getAllAirports();
	    Airport updateAirport(Airport airport);
	    void deleteAirport(Long airportId);
	}
