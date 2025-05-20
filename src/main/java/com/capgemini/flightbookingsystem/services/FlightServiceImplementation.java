package com.capgemini.flightbookingsystem.services;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.relation.InvalidRelationIdException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.dto.AirportFetchingDto;
import com.capgemini.flightbookingsystem.dto.CityWithAirportIdDto;
import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.exceptions.AirlineAdminNotFoundException;
import com.capgemini.flightbookingsystem.exceptions.FlightNotFoundException;
import com.capgemini.flightbookingsystem.exceptions.InvalidRequestException;
import com.capgemini.flightbookingsystem.repositories.AirLineAdminRepository;
import com.capgemini.flightbookingsystem.repositories.AirportRepository;
import com.capgemini.flightbookingsystem.repositories.BookingRepository;
import com.capgemini.flightbookingsystem.repositories.FlightRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FlightServiceImplementation implements FlightService {

	FlightRepository flightRepository;
	AirportRepository airportRepository;
	AirLineAdminRepository airLineAdminRepository;

	private static final String FLIGHT_NOT_FOUND_PREFIX = "Flight with Id ";
	private static final String ID_NOT_FOUND = "unknown";

	@Autowired
	public FlightServiceImplementation(FlightRepository flightRepository,
			AirportRepository airportRepository, AirLineAdminRepository airLineAdminRepository) {
		this.flightRepository = flightRepository;
		this.airportRepository = airportRepository;
		this.airLineAdminRepository = airLineAdminRepository;
	}

	@Override
	public List<Flights> getAllFlights() {
		log.info("Fetching all flights from the database");
		List<Flights> flights = flightRepository.findAll();
		log.debug("Total flights retrieved: {}", flights.size());
		return flights;
	}

	@Override
	public Flights getFlightById(Integer flightId) {
		log.info("Fetching Flight with ID: {}", flightId);
		return flightRepository.findById(flightId).orElseThrow(() -> {
			log.warn("Flight not found with ID: {}", flightId);
			return new FlightNotFoundException("Get : Flight not found with ID : " + flightId);
		});
	}

	@Override
	public Flights createNewFlight(@Valid Flights flight) {
		Integer adminId = null;
	    if (flight.getAirlineAdmin() != null) {
	        adminId = flight.getAirlineAdmin().getAirlineAdminId();
	    }

	    if (adminId == null) {
	        log.warn("AirlineAdmin ID not provided in request");
	        throw new InvalidRequestException("Airline Admin Id is required");
	    }

	    AirLineAdmin admin = airLineAdminRepository.findById(adminId)
	            .orElseThrow(() -> new AirlineAdminNotFoundException("AirlineAdmin not found"));

	    flight.setAirlineAdmin(admin);
		Flights newFlight = flightRepository.save(flight);
		log.debug("Flight created successfully with ID: {}", newFlight.getFlightId());
		return newFlight;
	}

	@Override
	public Flights patchFlightById(Integer flightId, Flights flight) {
		log.info("Updating flight with ID: {}", flightId);
		Flights existingFlight = flightRepository.findById(flightId).orElseThrow(() -> {
			log.warn("User not found for deletion with ID: {}", flightId);
			return new FlightNotFoundException(FLIGHT_NOT_FOUND_PREFIX + flightId + " not found");
		});

		if (flight.getFlightNumber() != null)
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
	public Flights updateFlightById(Integer flightId, @Valid Flights flight) {
		log.info("Updating flight with ID: {}", flightId);
		Flights existingFlight = flightRepository.findById(flightId).orElseThrow(() -> {
			log.warn("Flight not found for deletion with ID: {}", flightId);
			return new FlightNotFoundException(FLIGHT_NOT_FOUND_PREFIX + flightId + " is not available");
		});

		existingFlight.setFlightNumber(flight.getFlightNumber());
		existingFlight.setDepartureTime(flight.getDepartureTime());
		existingFlight.setArrivalTime(flight.getArrivalTime());
		existingFlight.setAmount(flight.getAmount());
		existingFlight.setAircraftModel(flight.getAircraftModel());
		existingFlight.setCapacity(flight.getCapacity());
		existingFlight.setArrivalAirportId(flight.getArrivalAirportId());
		existingFlight.setDepartureAirportId(flight.getDepartureAirportId());

		Flights updatedFlight = flightRepository.save(existingFlight);
		log.debug("Flight with ID {} updated successfully", flightId);
		return updatedFlight;
	}

	@Override
	public void deleteFlight(Integer flightId) {
		log.info("Attempting to delete flight with ID: {}", flightId);
		Flights existingFlight = flightRepository.findById(flightId).orElseThrow(() -> {
			log.warn("Delete failed. Flight not found with ID: {}", flightId);
			return new FlightNotFoundException(FLIGHT_NOT_FOUND_PREFIX + flightId + " is not available");
		});
		flightRepository.delete(existingFlight);
		log.debug("Flight with ID {} deleted successfully", flightId);
	}

	@Override
	public List<Flights> sortFlightsByNumber(String direction) {
		Sort sortedFlights = Sort.by("flightNumber");
		if (direction.equalsIgnoreCase("desc"))
			sortedFlights = sortedFlights.descending();
		else
			sortedFlights = sortedFlights.ascending();
		return flightRepository.findAll(sortedFlights);
	}

	@Override
	public List<Flights> sortFlightsByAmount(String direction) {
		Sort sortedFlights = Sort.by("amount");
		if (direction.equalsIgnoreCase("desc"))
			sortedFlights = sortedFlights.descending();
		else
			sortedFlights = sortedFlights.ascending();
		return flightRepository.findAll(sortedFlights);
	}

	@Override
	public AirportFetchingDto getFlightDTO(Integer flightId) {
		Flights flight = flightRepository.findById(flightId)
				.orElseThrow(() -> new RuntimeException("Flight not found"));

		String departureCity = airportRepository.findById(flight.getDepartureAirportId()).map(Airport::getCity)
				.orElse(ID_NOT_FOUND);

		String arrivalCity = airportRepository.findById(flight.getArrivalAirportId()).map(Airport::getCity)
				.orElse(ID_NOT_FOUND);

		return new AirportFetchingDto(departureCity, arrivalCity);
	}

	@Override
	public List<CityWithAirportIdDto>  getArrivalCities() {
		List<Flights> flights = flightRepository.findAll();
		Set<Integer> arrivalAirportIds = flights.stream()
	            .map(Flights::getArrivalAirportId)
	            .collect(Collectors.toSet());

		return arrivalAirportIds.stream()
	            .map(id -> airportRepository.findById(id)
	                    .map(airport -> new CityWithAirportIdDto(id, airport.getCity()))
	                    .orElse(null))
	            .filter(Objects::nonNull)
	            .distinct().toList();
	}

	@Override
	public List<String> getAircraftModels() {
		return flightRepository.findAll().stream().map(Flights::getAircraftModel).filter(Objects::nonNull).distinct()
				.toList();
	}

}
