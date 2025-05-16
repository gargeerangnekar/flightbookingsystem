package com.capgemini.flightbookingsystem.testcontrollers;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.flightbookingsystem.controllers.FlightRestController;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.services.FlightService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

//Test 
class FlightControllerTest {

    @Mock
    private FlightService flightService;
    
    @InjectMocks
    private FlightRestController flightRestController;
    
    private Flights flight1;
    private Flights flight2;
    
    @BeforeEach
    void setUpFlight() {
    	MockitoAnnotations.openMocks(this);
    	flight1 = new Flights(1, "AI202", LocalDateTime.parse("2025-06-01T10:30:00"), LocalDateTime.parse("2025-06-01T13:45:00"),
                "Scheduled",12000.0,"Boeing 737",180, 1, 3);
    	flight2 = new Flights(2, "BJ301", LocalDateTime.parse("2025-06-01T10:30:00"), LocalDateTime.parse("2025-06-01T13:45:00"),
                "Delayed",9000.0,"Boeing 737",180, 2, 3);
    }
    
    //1. Test Case for POST Mapping
    @Test
    @DisplayName("Test to create a flight")
    void testCreateFlight() {
    	BindingResult bindingResult = mock(BindingResult.class);
    	when(flightService.createNewFlight(flight1)).thenReturn(flight1);
        ResponseEntity<Flights> response = flightRestController.createFlight(flight1, bindingResult);
        assertEquals(201, response.getStatusCode().value());
		assertEquals(flight1, response.getBody());
    }
    
    
    //2. Test Case for GET Mapping
    @Test
    @DisplayName("Test to get all flights")
    void testGetAllFlights() {
    	List<Flights> flights = Arrays.asList(flight1, flight2);
		when(flightService.getAllFlights()).thenReturn(flights);

		ResponseEntity<List<Flights>> response = flightRestController.getAllFlights();

		assertEquals(200, response.getStatusCode().value());
		assertEquals(2, flights.size());
    }
    
    //3. Test Case for GET Mapping By Id
    @Test
    @DisplayName("Test to get flight by ID")
    void testGetFlightById() {
		when(flightService.getFlightById(1)).thenReturn(flight1);

		ResponseEntity<Flights> response = flightRestController.getFlightById(1);

		assertEquals(200, response.getStatusCode().value());
		assertEquals(1, response.getBody().getFlightId());
    }
    
    
    //4. Test Case for PUT Mapping
    @Test
    @DisplayName("Test to update a flight")
    void testUpdateFlight() {
    	Flights updateFlight = new Flights(3, "CL507", LocalDateTime.parse("2025-06-01T10:30:00"), LocalDateTime.parse("2025-06-01T13:45:00"),
                "Scheduled",21000.0,"Boeing 737",200, 1, 1);
        when(flightService.updateFlightById(3, updateFlight)).thenReturn(updateFlight);
        ResponseEntity<Flights> response = flightRestController.updateFlight(3, updateFlight);
        assertEquals(200, response.getStatusCode().value());
		assertEquals(updateFlight, response.getBody());
    }
    
    
    //5. Test Case for DELETE Mapping
    @Test
    @DisplayName("Test to delete a flight by ID")
    void testDeleteFlight() {
        doNothing().when(flightService).deleteFlight(1);
        ResponseEntity<Flights> response = flightRestController.deleteFlight(1);
        assertEquals(204, response.getStatusCode().value());
    }

}
