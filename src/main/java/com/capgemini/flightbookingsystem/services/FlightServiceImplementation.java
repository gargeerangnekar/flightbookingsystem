package com.capgemini.flightbookingsystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.exceptions.FlightNotFoundException;
import com.capgemini.flightbookingsystem.repositories.FlightRepository;

@Service
public class FlightServiceImplementation implements FlightService {
	
	//Injecting repository
	FlightRepository flightRepository;
	
	@Autowired
	public FlightServiceImplementation(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	@Override
	public List<Flights> getAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	public Flights getFlightById(Integer flightId) {
		return flightRepository.findById(flightId)
				.orElseThrow(()-> new FlightNotFoundException("Flight with Id "+flightId+" is not available"));
	}

	@Override
	public Flights createNewFlight(Flights flight) {
		return flightRepository.save(flight);
	}

	@Override
	public Flights updateFlightById(Integer flightId, Flights flight) {
		Flights existingFlight = flightRepository.findById(flightId)
				.orElseThrow(()-> new FlightNotFoundException("Flight with Id "+flightId+" is not available"));
		
		if(flight.getFlightNumber() != null)
			existingFlight.setFlightNumber(flight.getFlightNumber());
		
		if (flight.getDepartureTime() != null)
	        existingFlight.setDepartureTime(flight.getDepartureTime());

	    if (flight.getArrivalTime() != null)
	        existingFlight.setArrivalTime(flight.getArrivalTime());

	    if (flight.getStatus() != null)
	        existingFlight.setStatus(flight.getStatus());

	    if (flight.getAircraftModel() != null)
	        existingFlight.setAircraftModel(flight.getAircraftModel());

	    if (flight.getCapacity() != null)
	        existingFlight.setCapacity(flight.getCapacity());

	    if (flight.getAirlineAdminId() != null)
	        existingFlight.setAirlineAdminId(flight.getAirlineAdminId());

	    if (flight.getArrivalAirportId() != null)
	        existingFlight.setArrivalAirportId(flight.getArrivalAirportId());

	    if (flight.getDepartureAirportId() != null)
	        existingFlight.setDepartureAirportId(flight.getDepartureAirportId());

	    return flightRepository.save(existingFlight);
	}

	@Override
	public void deleteFlight(Integer flightId) {
		Flights existingFlight = flightRepository.findById(flightId)
				.orElseThrow(()-> new FlightNotFoundException("Flight with Id "+flightId+" not found"));
		
		flightRepository.delete(existingFlight);
	}

	@Override
	public Booking createBookingForFlight(Integer flightId, Booking booking) {
		Flights flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException("Flight with Id "+flightId+" not found"));
		booking.setFlights(flight);
		flight.getBookings().add(booking);
		flightRepository.save(flight);
		return booking;
	}

}
