package com.capgemini.flightbookingsystem.testcontrollers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.flightbookingsystem.controllers.FlightRestController;
import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.repositories.AirLineAdminRepository;
import com.capgemini.flightbookingsystem.services.FlightService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

class FlightControllerTest {

	@Mock
	private FlightService flightService;

	@Mock
	private AirLineAdminRepository airLineAdminRepository;

	@InjectMocks
	private FlightRestController flightRestController;

	private Flights flight1;
	private Flights flight2;
	private AirLineAdmin admin;

	@BeforeEach
	void setUpFlight() {
		MockitoAnnotations.openMocks(this);

		admin = new AirLineAdmin(
			1,
			"John Smith",
			"Admin@123",
			"9876543210",
			"admin@example.com"
		);

		when(airLineAdminRepository.findById(1)).thenReturn(Optional.of(admin));
		
		flight1 = new Flights();
		flight1.setFlightId(1);
		flight1.setFlightNumber("AI202");
		flight1.setDepartureTime(LocalDateTime.parse("2025-06-01T10:30:00"));
		flight1.setArrivalTime(LocalDateTime.parse("2025-06-01T13:45:00"));
		flight1.setAmount(5000.0);
		flight1.setAircraftModel("Boeing 737");
		flight1.setCapacity(180);
		flight1.setArrivalAirportId(1);
		flight1.setDepartureAirportId(3);
		flight1.setAirlineAdmin(admin);
		
		flight2 = new Flights();
		flight2.setFlightId(2);
		flight2.setFlightNumber("BJ301");
		flight2.setDepartureTime(LocalDateTime.parse("2025-06-01T10:30:00"));
		flight2.setArrivalTime(LocalDateTime.parse("2025-06-01T13:45:00"));
		flight2.setAmount(2000.0);
		flight2.setAircraftModel("Boeing 737");
		flight2.setCapacity(180);
		flight2.setArrivalAirportId(2);
		flight2.setDepartureAirportId(3);
		flight2.setAirlineAdmin(admin);
	}
		

	@Test
	@DisplayName("Test to create a flight")
	void testCreateFlight() {
		BindingResult bindingResult = mock(BindingResult.class);
		when(flightService.createNewFlight(flight1)).thenReturn(flight1);

		ResponseEntity<Flights> response = flightRestController.createFlight(flight1, bindingResult);

		assertEquals(201, response.getStatusCode().value());
		assertEquals(flight1, response.getBody());
	}

	@Test
	@DisplayName("Test to get all flights")
	void testGetAllFlights() {
		List<Flights> flights = Arrays.asList(flight1, flight2);
		when(flightService.getAllFlights()).thenReturn(flights);

		ResponseEntity<List<Flights>> response = flightRestController.getAllFlights();

		assertEquals(200, response.getStatusCode().value());
		assertEquals(2, flights.size());
	}

	@Test
	@DisplayName("Test to get flight by ID")
	void testGetFlightById() {
		when(flightService.getFlightById(1)).thenReturn(flight1);

		ResponseEntity<Flights> response = flightRestController.getFlightById(1);

		assertEquals(200, response.getStatusCode().value());
		assertEquals(1, response.getBody().getFlightId());
	}

	@Test
	@DisplayName("Test to update a flight")
	void testUpdateFlight() {
		Flights updateFlight;
		updateFlight = new Flights();
		updateFlight.setFlightId(3);
		updateFlight.setFlightNumber("CL507");
		updateFlight.setDepartureTime(LocalDateTime.parse("2025-06-01T10:30:00"));
		updateFlight.setArrivalTime(LocalDateTime.parse("2025-06-01T13:45:00"));
		updateFlight.setAmount(21000.0);
		updateFlight.setAircraftModel("Boeing 737");
		updateFlight.setCapacity(200);
		updateFlight.setArrivalAirportId(1);
		updateFlight.setDepartureAirportId(1);
		updateFlight.setAirlineAdmin(admin);

		when(flightService.updateFlightById(3, updateFlight)).thenReturn(updateFlight);

		ResponseEntity<Flights> response = flightRestController.updateFlight(3, updateFlight);

		assertEquals(200, response.getStatusCode().value());
		assertEquals(updateFlight, response.getBody());
	}

	@Test
	@DisplayName("Test to delete a flight by ID")
	void testDeleteFlight() {
		doNothing().when(flightService).deleteFlight(1);

		ResponseEntity<Flights> response = flightRestController.deleteFlight(1);

		assertEquals(204, response.getStatusCode().value());
	}
	
	@Test
	@DisplayName("Test to get flights sorted by flight number ascending")
	void testGetSortedFlightsByNumberAsc() {
	    when(flightService.sortFlightsByNumber("asc")).thenReturn(Arrays.asList(flight1, flight2));

	    ResponseEntity<List<Flights>> response = flightRestController.getSortedFlightsByNumber("asc");

	    assertEquals(200, response.getStatusCode().value());
	    assertEquals(2, response.getBody().size());
	}
	
	@Test
	@DisplayName("Test to get flights sorted by amount descending")
	void testGetSortedFlightsByAmountDesc() {
	    when(flightService.sortFlightsByAmount("desc")).thenReturn(Arrays.asList(flight1, flight2));

	    ResponseEntity<List<Flights>> response = flightRestController.getSortedAmount("desc");

	    assertEquals(200, response.getStatusCode().value());
	    assertEquals(2, response.getBody().size());
	}

}
