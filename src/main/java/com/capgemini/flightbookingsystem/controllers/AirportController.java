package com.capgemini.flightbookingsystem.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.services.AirportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/airports")
public class AirportController {

	private static final Logger logger = LoggerFactory.getLogger(AirportController.class);

	private final AirportService airportService;

	@Autowired
	public AirportController(AirportService airportService) {
		this.airportService = airportService;
	}

	@PostMapping
	public ResponseEntity<Airport> createAirport(@Valid @RequestBody Airport airport, BindingResult result) {
		logger.info("Received request to create a new airport: {}", airport.getAirportName());
		
		if (result.hasErrors()) {
			logger.warn("Validation failed for Airport {}:", result.getAllErrors());
			throw new IllegalArgumentException("Cannot insert in Airport");
		}
		
		Airport savedAirport = airportService.saveAirport(airport);
		logger.debug("Airport created successfully with ID: {}", savedAirport.getAirportId());
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAirport);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Airport> getAirport(@PathVariable Integer id) {
		logger.info("Fetching airport with ID: {}", id);

		Airport airport = airportService.getAirportById(id);
		logger.debug("Successfully fetched airport: {}", airport);
		return ResponseEntity.status(HttpStatus.OK).body(airport);

	}

	@GetMapping
	public ResponseEntity<List<Airport>> getAllAirports() {
		logger.info("Fetching list of all airports");

		List<Airport> airports = airportService.getAllAirports();
		logger.debug("Fetched {} airports", airports.size());
		return ResponseEntity.status(HttpStatus.OK).body(airports);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Airport> updateAirport(@Valid @PathVariable Integer id, @RequestBody Airport airportDetails,BindingResult result) {
		logger.info("Fetching airport with ID: {}", id);
		
		if (result.hasErrors()) {
			logger.warn("Validation failed for Airport {}:", result.getAllErrors());
			throw new IllegalArgumentException("Cannot update in Airport");
		}
		
		airportDetails.setAirportId(id);
		Airport updatedAirport = airportService.updateAirport(airportDetails, id);
		logger.debug("Airport updated successfully: {}", updatedAirport);
		return ResponseEntity.ok(updatedAirport);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAirport(@PathVariable Integer id) {
		logger.info("Deleting airport with ID: {}", id);
		airportService.deleteAirport(id);
		logger.debug("Airport with ID {} deleted successfully", id);
		return ResponseEntity.noContent().build();

	}
}
