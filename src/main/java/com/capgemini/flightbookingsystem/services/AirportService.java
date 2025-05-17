package com.capgemini.flightbookingsystem.services;

import java.util.List;

import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.entities.Flights;

//7
public interface AirportService {

    Airport saveAirport(Airport airport);
    Airport getAirportById(Integer airportId);
    List<Airport> getAllAirports();
    Airport updateAirport(Airport airport, Integer airportId);
    void deleteAirport(Integer airportId);
    
}