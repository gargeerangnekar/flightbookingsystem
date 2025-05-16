package com.capgemini.flightbookingsystem.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.services.AirportService;

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
	public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
		logger.info("Received request to create a new airport: {}", airport.getAirportName());
		try {
			Airport savedAirport = airportService.saveAirport(airport);
			logger.debug("Airport created successfully with ID: {}", savedAirport.getAirportId());
			return ResponseEntity.status(HttpStatus.CREATED).body(savedAirport);
		} catch (Exception e) {
			logger.error("Error occurred while creating airport: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Airport> getAirport(@PathVariable Integer id) {
		logger.info("Fetching airport with ID: {}", id);
		try {
			Airport airport = airportService.getAirportById(id);
			logger.debug("Successfully fetched airport: {}", airport);
			return ResponseEntity.status(HttpStatus.OK).body(airport);
		} catch (Exception e) {
			logger.error("Error fetching airport with ID {}: {}", id, e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Airport>> getAllAirports() {
		logger.info("Fetching list of all airports");
		try {
			List<Airport> airports = airportService.getAllAirports();
			logger.debug("Fetched {} airports", airports.size());
			return ResponseEntity.status(HttpStatus.OK).body(airports);
		} catch (Exception e) {
			logger.error("Error fetching airport list: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Airport> updateAirport(@PathVariable Integer id, @RequestBody Airport airportDetails) {
		logger.info("Updating airport with ID: {}", id);
		try {
			airportDetails.setAirportId(id);
			Airport updatedAirport = airportService.updateAirport(airportDetails);
			logger.debug("Airport updated successfully: {}", updatedAirport);
			return ResponseEntity.ok(updatedAirport);
		} catch (Exception e) {
			logger.error("Error updating airport with ID {}: {}", id, e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAirport(@PathVariable Integer id) {
		logger.info("Deleting airport with ID: {}", id);
		try {
			airportService.deleteAirport(id);
			logger.debug("Airport with ID {} deleted successfully", id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			logger.error("Error deleting airport with ID {}: {}", id, e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
