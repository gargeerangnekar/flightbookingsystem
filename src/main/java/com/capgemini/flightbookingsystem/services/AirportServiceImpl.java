package com.capgemini.flightbookingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.exceptions.AirportNotFoundException;
import com.capgemini.flightbookingsystem.repositories.AirportRepository;

@Service
public class AirportServiceImpl implements AirportService{

	 @Autowired
	    private AirportRepository airportRepository;

	    @Override
	    public Airport saveAirport(Airport airport) {
	        return airportRepository.save(airport);
	    }

	    @Override
	    public Airport getAirportById(Long airportId) {
	        return airportRepository.findById(airportId)
	                .orElseThrow(() -> new AirportNotFoundException("Airport ID not found :"+airportId));
	    }

	    @Override
	    public List<Airport> getAllAirports() {
	        return airportRepository.findAll();
	    }
	    
	    @Override
	    public List<Flights> getFlightsByAirport(Long airportId) {
	    	return airportRepository.findByAirportAirportId(airportId);
	    }

	    @Override
	    public Airport updateAirport(Airport airport) {
	  
	        Airport existingAirport = airportRepository.findById(airport.getAirportId())
	                .orElseThrow(() -> new AirportNotFoundException("Airport ID not found :"+airport.getAirportId()));
	        
	        existingAirport.setAirportName(airport.getAirportName());
	        existingAirport.setCity(airport.getCity());
	        existingAirport.setContact(airport.getContact());
	        return airportRepository.save(existingAirport);
	    }

	    @Override
	    public void deleteAirport(Long airportId) {
	        
	        if (!airportRepository.existsById(airportId)) {
	            throw new AirportNotFoundException("Airport ID not found :"+airportId);
	        }
	        airportRepository.deleteById(airportId);
	    }
}
