package com.capgemini.flightbookingsystem.testcontrollers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.capgemini.flightbookingsystem.controllers.FlightRestController;
import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.repositories.AirLineAdminRepository;
import com.capgemini.flightbookingsystem.services.FlightService;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @Mock
    private AirLineAdminRepository airLineAdminRepository;

    @InjectMocks
    private FlightRestController flightRestController;

    private AirLineAdmin admin;
    private Flights flight1;
    private Flights flight2;

    @BeforeEach
    void setUp() {
        // common admin stub
        admin = new AirLineAdmin(
            1,
            "John Smith",
            "Admin@123",
            "9876543210",
            "admin@example.com"
        );
        when(airLineAdminRepository.findById(1)).thenReturn(Optional.of(admin));

        // test data
        flight1 = new Flights(
            1,
            "AI202",
            LocalDateTime.parse("2025-06-01T10:30:00"),
            LocalDateTime.parse("2025-06-01T13:45:00"),
            5000.0,
            "Boeing 737",
            180,
            1,
            3,
            null,
            admin
        );
        flight2 = new Flights(
            2,
            "BJ301",
            LocalDateTime.parse("2025-06-01T10:30:00"),
            LocalDateTime.parse("2025-06-01T13:45:00"),
            2000.0,
            "Boeing 737",
            180,
            2,
            3,
            null,
            admin
        );
    }

    @Test
    @DisplayName("Test to create a flight")
    void testCreateFlight() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(flightService.createNewFlight(flight1)).thenReturn(flight1);

        ResponseEntity<Flights> response = flightRestController.createFlight(flight1, bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(flight1, response.getBody());
    }

    @Test
    @DisplayName("Test to get all flights")
    void testGetAllFlights() {
        List<Flights> flights = Arrays.asList(flight1, flight2);
        when(flightService.getAllFlights()).thenReturn(flights);

        ResponseEntity<List<Flights>> response = flightRestController.getAllFlights();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(flights, response.getBody());
    }

    @Test
    @DisplayName("Test to get flight by ID")
    void testGetFlightById() {
        when(flightService.getFlightById(1)).thenReturn(flight1);

        ResponseEntity<Flights> response = flightRestController.getFlightById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(flight1, response.getBody());
    }

    @Test
    @DisplayName("Test to update a flight")
    void testUpdateFlight() {
        Flights updated = new Flights(
            3,
            "CL507",
            LocalDateTime.parse("2025-06-01T10:30:00"),
            LocalDateTime.parse("2025-06-01T13:45:00"),
            21000.0,
            "Boeing 737",
            200,
            1,
            1,
            null,
            admin
        );
        when(flightService.updateFlightById(3, updated)).thenReturn(updated);

        ResponseEntity<Flights> response = flightRestController.updateFlight(3, updated);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());
    }

    @Test
    @DisplayName("Test to delete a flight by ID")
    void testDeleteFlight() {
        doNothing().when(flightService).deleteFlight(1);

        ResponseEntity<Flights> response = flightRestController.deleteFlight(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("Test to get flights sorted by flight number ascending")
    void testGetSortedFlightsByNumberAsc() {
        when(flightService.sortFlightsByNumber("asc"))
            .thenReturn(Arrays.asList(flight1, flight2));

        ResponseEntity<List<Flights>> response = flightRestController.getSortedFlightsByNumber("asc");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    @DisplayName("Test to get flights sorted by amount descending")
    void testGetSortedFlightsByAmountDesc() {
        when(flightService.sortFlightsByAmount("desc"))
            .thenReturn(Arrays.asList(flight1, flight2));

        ResponseEntity<List<Flights>> response = flightRestController.getSortedAmount("desc");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }
}
