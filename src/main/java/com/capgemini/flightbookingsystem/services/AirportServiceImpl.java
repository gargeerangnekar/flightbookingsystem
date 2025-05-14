package com.capgemini.flightbookingsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.exceptions.AirportNotFoundException;
import com.capgemini.flightbookingsystem.repositories.AirportRepository;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportRepository airportRepository;

	@Override
	public Airport saveAirport(Airport airport) {
		return airportRepository.save(airport);
	}

	@Override
	public Airport getAirportById(Long airportId) {
		return airportRepository.findById(airportId).orElseThrow(() -> new AirportNotFoundException(airportId));
	}

	@Override
	public List<Airport> getAllAirports() {
		return airportRepository.findAll();
	}

	@Override
	public Airport updateAirport(Airport airport) {
		Airport existingAirport = airportRepository.findById(airport.getAirportId())
				.orElseThrow(() -> new AirportNotFoundException(airport.getAirportId()));

		existingAirport.setAirportName(airport.getAirportName());
		existingAirport.setCity(airport.getCity());
		existingAirport.setContact(airport.getContact());
		return airportRepository.save(existingAirport);
	}

	@Override
	public void deleteAirport(Long airportId) {
		if (!airportRepository.existsById(airportId)) {
			throw new AirportNotFoundException(airportId);
		}
		airportRepository.deleteById(airportId);
	}

}
