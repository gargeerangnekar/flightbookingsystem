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

import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.services.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightRestController{
	
	
	//Injecting service layer
	FlightService flightService;

	@Autowired
	public FlightRestController(FlightService flightService) {
		this.flightService = flightService;
	}
	
	@GetMapping
	public ResponseEntity<List<Flights>> getAllFlights(){
		return ResponseEntity.status(HttpStatus.OK).body(flightService.getAllFlights());
	}
	
	@GetMapping("/{flightId}")
	public ResponseEntity<Flights> getFlightById(@PathVariable Integer flightId){
		return ResponseEntity.status(HttpStatus.OK).body(flightService.getFlightById(flightId));
	}
	
	@PostMapping
	public ResponseEntity<Flights> createFlight(@RequestBody Flights flight){
		return ResponseEntity.status(HttpStatus.CREATED).body(flightService.createNewFlight(flight));
	}
	
	@PutMapping("/{flightId}")
	public ResponseEntity<Flights> updateFlight(@PathVariable Integer flightId ,@RequestBody Flights flight){
		return ResponseEntity.status(HttpStatus.OK).body(flightService.updateFlightById(flightId, flight));
	}
	
	@DeleteMapping("/{flightId}")
	public ResponseEntity<Flights> deleteFlight(@PathVariable Integer flightId){
		flightService.deleteFlight(flightId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
