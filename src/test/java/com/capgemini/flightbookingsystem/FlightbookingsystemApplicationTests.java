package com.capgemini.flightbookingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.capgemini.flightbookingsystem.controllers.FlightRestController;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.services.FlightService;

@SpringBootTest
class FlightbookingsystemApplicationTests {

	@Mock
	FlightService flightService;
	
	@InjectMocks
	FlightRestController flightRestController;
	
	Flights sampleFlight;

	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);

	    sampleFlight = new Flights();
	    sampleFlight.setFlightId(1);
	    sampleFlight.setFlightNumber("AI202");
	    sampleFlight.setDepartureTime(LocalDateTime.of(2025, 5, 20, 10, 30));
	    sampleFlight.setArrivalTime(LocalDateTime.of(2025, 5, 20, 12, 45));
	    sampleFlight.setStatus("Scheduled");
	    sampleFlight.setAircraftModel("Boeing 737");
	    sampleFlight.setCapacity(180);
	    sampleFlight.setAirlineAdminId(1001);
	    sampleFlight.setArrivalAirportId(501);
	    sampleFlight.setDepartureAirportId(502);
	}
	
	@Test
    void testGetAllFlights() {
        List<Flights> flights = Arrays.asList(sampleFlight);
        when(flightService.getAllFlights()).thenReturn(flights);

        ResponseEntity<List<Flights>> response = flightRestController.getAllFlights();

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("AI202", response.getBody().get(0).getFlightNumber());
        verify(flightService, times(1)).getAllFlights();
    }

    @Test
    void testGetFlightById() {
        when(flightService.getFlightById(1)).thenReturn(sampleFlight);

        ResponseEntity<Flights> response = flightRestController.getFlightById(1);

        assertNotNull(response.getBody());
        assertEquals("AI202", response.getBody().getFlightNumber());
        verify(flightService, times(1)).getFlightById(1);
    }

    @Test
    void testCreateFlight() {
        when(flightService.createNewFlight(sampleFlight)).thenReturn(sampleFlight);

        ResponseEntity<Flights> response = flightRestController.createFlight(sampleFlight);
        assertNotNull(response.getBody());
        assertEquals("AI202", response.getBody().getFlightNumber());
        verify(flightService, times(1)).createNewFlight(sampleFlight);
    }

    @Test
    void testUpdateFlight() {
        Flights updatedFlight = new Flights();
        updatedFlight.setFlightId(1);
        updatedFlight.setFlightNumber("AI203");
        updatedFlight.setDepartureTime(LocalDateTime.of(2025, 5, 21, 11, 0));
        updatedFlight.setArrivalTime(LocalDateTime.of(2025, 5, 21, 13, 15));
        updatedFlight.setStatus("Delayed");
        updatedFlight.setAircraftModel("Airbus A320");
        updatedFlight.setCapacity(200);
        updatedFlight.setAirlineAdminId(1001);
        updatedFlight.setArrivalAirportId(502);
        updatedFlight.setDepartureAirportId(501);

        when(flightService.updateFlightById(1, updatedFlight)).thenReturn(updatedFlight);

        ResponseEntity<Flights> response = flightRestController.updateFlight(1, updatedFlight);

        assertNotNull(response.getBody());
        assertEquals("AI203", response.getBody().getFlightNumber());
        verify(flightService, times(1)).updateFlightById(1, updatedFlight);
    }

    @Test
    void testDeleteFlight() {
        doNothing().when(flightService).deleteFlight(1);

        ResponseEntity<Flights> response = flightRestController.deleteFlight(1);

        assertNull(response.getBody());
        verify(flightService, times(1)).deleteFlight(1);
    }

}
