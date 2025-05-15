package com.capgemini.flightbookingsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.services.AirportService;

@RestController
@RequestMapping("/airports")
public class AirportController {

	private final AirportService airportService;

	@Autowired
	public AirportController(AirportService airportService) {
		this.airportService = airportService;
	}

	@PostMapping
	public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
		Airport savedAirport = airportService.saveAirport(airport);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAirport);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Airport> getAirport(@PathVariable Long id) {
		Airport airport = airportService.getAirportById(id);
		return ResponseEntity.status(HttpStatus.OK).body(airport);
	}

	@GetMapping
	public ResponseEntity<List<Airport>> getAllAirports() {
		List<Airport> airports = airportService.getAllAirports();
		return ResponseEntity.status(HttpStatus.OK).body(airports);
	}
	
//	@GetMapping
//	public ResponseEntity<List<Flights>> getAllFlightsByAirport(){
//		List<Flights> flights = airportService.getAllFlightsByAirport();
//		return flights;
//	}

	@PutMapping("/{id}")
	public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airportDetails) {
		airportDetails.setAirportId(id);
		Airport updatedAirport = airportService.updateAirport(airportDetails);
		return ResponseEntity.ok(updatedAirport);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
		airportService.deleteAirport(id);
		return ResponseEntity.noContent().build();
	}
}
