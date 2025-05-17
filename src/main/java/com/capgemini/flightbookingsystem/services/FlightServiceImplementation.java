package com.capgemini.flightbookingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.dto.AirportFetchingDto;
import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.exceptions.FlightNotFoundException;
import com.capgemini.flightbookingsystem.repositories.AirportRepository;
import com.capgemini.flightbookingsystem.repositories.BookingRepository;
import com.capgemini.flightbookingsystem.repositories.FlightRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FlightServiceImplementation implements FlightService {
	
	//Injecting repository
	FlightRepository flightRepository;
	BookingRepository bookingRepository;
	AirportRepository airportRepository;
	
	@Autowired
	public FlightServiceImplementation(FlightRepository flightRepository, BookingRepository bookingRepository, AirportRepository airportRepository) {
		this.flightRepository = flightRepository;
		this.bookingRepository = bookingRepository;
		this.airportRepository = airportRepository;
	}

	@Override
	public List<Flights> getAllFlights() {
		log.info("Fetching all flights from the database");
		List<Flights> flights =  flightRepository.findAll();
		log.debug("Total flights retrieved: {}", flights.size());
		return flights;
	}

	@Override
	public Flights getFlightById(Integer flightId) {
		log.info("Fetching Flight with ID: {}", flightId);
		return flightRepository.findById(flightId)
				.orElseThrow(() -> {
					log.warn("Flight not found with ID: {}", flightId);
					return new FlightNotFoundException("Get : Flight not found with ID : " + flightId);
				});
	}

	@Override
	public Flights createNewFlight(@Valid Flights flight) {
		Flights newFlight = flightRepository.save(flight);
		log.debug("Flight created successfully with ID: {}", newFlight.getFlightId());
		return newFlight;
	}

	@Override
	public Flights updateFlightById(Integer flightId, @Valid Flights flight) {
		log.info("Updating flight with ID: {}", flightId);
		Flights existingFlight = flightRepository.findById(flightId)
				.orElseThrow(()-> {
					log.warn("User not found for deletion with ID: {}", flightId);
					return new FlightNotFoundException("Flight with Id "+flightId+" is not available");
					});
		
		if(flight.getFlightNumber() != null)
			existingFlight.setFlightNumber(flight.getFlightNumber());
		
		if (flight.getDepartureTime() != null)
	        existingFlight.setDepartureTime(flight.getDepartureTime());

	    if (flight.getArrivalTime() != null)
	        existingFlight.setArrivalTime(flight.getArrivalTime());

	    
	    if (flight.getAmount() != null)
	        existingFlight.setAmount(flight.getAmount());

	    if (flight.getAircraftModel() != null)
	        existingFlight.setAircraftModel(flight.getAircraftModel());

	    if (flight.getCapacity() != null)
	        existingFlight.setCapacity(flight.getCapacity());

	    if (flight.getArrivalAirportId() != null)
	        existingFlight.setArrivalAirportId(flight.getArrivalAirportId());

	    if (flight.getDepartureAirportId() != null)
	        existingFlight.setDepartureAirportId(flight.getDepartureAirportId());

	    
	    Flights updatedFlight = flightRepository.save(existingFlight);
	    log.debug("Flight with ID {} updated successfully", flightId);
	    return updatedFlight;
	}

	@Override
	public void deleteFlight(Integer flightId) {
		log.info("Attempting to delete flight with ID: {}", flightId);
		Flights existingFlight = flightRepository.findById(flightId)
				.orElseThrow(()-> {
					log.warn("Delete failed. Flight not found with ID: {}", flightId);
					return new FlightNotFoundException("Flight with Id "+flightId+" is not available");
					});
		flightRepository.delete(existingFlight);
		log.debug("Flight with ID {} deleted successfully", flightId);
	}

	@Override
	public List<Flights> sortFlightsByNumber(String direction) {
		Sort sortedFlights = Sort.by("flightNumber");
		if(direction.equalsIgnoreCase("desc")) 
			sortedFlights = sortedFlights.descending();
		else 
			sortedFlights = sortedFlights.ascending();
		return flightRepository.findAll(sortedFlights);
	}

	@Override
	public List<Flights> sortFlightsByAmount(String direction) {
		Sort sortedFlights = Sort.by("amount");
		if(direction.equalsIgnoreCase("desc")) 
			sortedFlights = sortedFlights.descending();
		else 
			sortedFlights = sortedFlights.ascending();
		return flightRepository.findAll(sortedFlights);
	}

	@Override
	public AirportFetchingDto getFlightDTO(Integer flightId) {
        Flights flight = flightRepository.findById(flightId)
            .orElseThrow(() -> new RuntimeException("Flight not found"));

        String departureCity = airportRepository.findById(flight.getDepartureAirportId())
            .map(Airport::getCity)
            .orElse("Unknown");

        String arrivalCity = airportRepository.findById(flight.getArrivalAirportId())
            .map(Airport::getCity)
            .orElse("Unknown");

        return new AirportFetchingDto(departureCity, arrivalCity);
    }

}
