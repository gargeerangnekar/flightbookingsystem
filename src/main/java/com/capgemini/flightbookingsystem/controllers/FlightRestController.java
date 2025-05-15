package com.capgemini.flightbookingsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.services.FlightService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/flights")
@Slf4j
public class FlightRestController{
	
	
	//Injecting service layer
	FlightService flightService;

	@Autowired
	public FlightRestController(FlightService flightService) {
		this.flightService = flightService;
	}
	
	@GetMapping
	public ResponseEntity<List<Flights>> getAllFlights(){
		log.info("Fetching all flights");
		List<Flights> flights = flightService.getAllFlights();
		log.debug("Total flights fetched: {}", flights.size());
		return ResponseEntity.status(HttpStatus.OK).body(flights);
	}
	
	@GetMapping("/{flightId}")
	public ResponseEntity<Flights> getFlightById(@PathVariable Integer flightId){
		log.info("Fetching flight with ID: {}", flightId);
		Flights flight = flightService.getFlightById(flightId);
		log.debug("Fetched flight details: {}", flight);
		return ResponseEntity.status(HttpStatus.OK).body(flight);
	}
	
	@PostMapping
	public ResponseEntity<Flights> createFlight(@RequestBody Flights flight){
		log.info("Creating new flight with data: {}", flight);
		Flights newFlight = flightService.createNewFlight(flight);
		log.debug("Flight created successfully with ID: {}", newFlight.getFlightId());
		return ResponseEntity.status(HttpStatus.CREATED).body(newFlight);
	}
	
	@PutMapping("/{flightId}")
	public ResponseEntity<Flights> updateFlight(@PathVariable Integer flightId ,@RequestBody Flights flight){
		log.info("Updating flight with ID: {} using data: {}", flightId, flight);
		Flights updatedFlight = flightService.updateFlightById(flightId, flight);
		log.debug("User with ID {} updated successfully to: {}", flightId, updatedFlight);
		return ResponseEntity.status(HttpStatus.OK).body(updatedFlight);
	}
	
	@DeleteMapping("/{flightId}")
	public ResponseEntity<Flights> deleteFlight(@PathVariable Integer flightId){
		log.info("Deleting flight with ID: {}", flightId);
		flightService.deleteFlight(flightId);
		log.debug("Flight with ID {} deleted successfully", flightId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/{flightId}/bookings")
	public ResponseEntity<Booking> createBookingForFlight(@PathVariable Integer flightId ,@RequestBody Booking booking){
		log.info("Creating booking for flight ID: {} with booking details: {}", flightId, booking);
		Booking createdBooking = flightService.createBookingForFlight(flightId, booking);
		log.debug("Booking created successfully for flight ID: {} with booking ID: {}", flightId, createdBooking.getBookingId());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
	}
}
