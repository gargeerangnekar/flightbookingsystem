package com.capgemini.flightbookingsystem.testcontrollers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.capgemini.flightbookingsystem.controllers.AirportController;
import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.services.AirportService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class AirportControllerTest {

    @Mock
    private AirportService airportService;

    @InjectMocks
    private AirportController airportController;

    private Airport sampleAirport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleAirport = new Airport();
        sampleAirport.setAirportId(1);
        sampleAirport.setAirportName("Test Airport");
        sampleAirport.setCity("Test City");
        sampleAirport.setContact("1234567890");
    }

    @Test
    @DisplayName("Constructor Injection - Should create controller instance")
    void testConstructorInjection() {
        AirportService mockService = mock(AirportService.class);
        AirportController controller = new AirportController(mockService);
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Create Airport - Success")
    void testCreateAirport() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        when(airportService.saveAirport(any(Airport.class))).thenReturn(sampleAirport);

        ResponseEntity<Airport> response = airportController.createAirport(sampleAirport, bindingResult);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sampleAirport, response.getBody());
    }

    @Test
    @DisplayName("Create Airport - Validation Failure")
    void testCreateAirportValidationFails() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            airportController.createAirport(sampleAirport, bindingResult);
        });

        assertEquals("Cannot insert in Airport", exception.getMessage());
    }

    @Test
    @DisplayName("Get Airport by ID - Success")
    void testGetAirportById() {
        when(airportService.getAirportById(1)).thenReturn(sampleAirport);

        ResponseEntity<Airport> response = airportController.getAirport(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleAirport, response.getBody());
    }

    @Test
    @DisplayName("Get Airport by ID - Not Found (returns null)")
    void testGetAirportNotFound() {
        when(airportService.getAirportById(99)).thenReturn(null);

        ResponseEntity<Airport> response = airportController.getAirport(99);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("Get All Airports - Success")
    void testGetAllAirports() {
        List<Airport> airports = Arrays.asList(sampleAirport);
        when(airportService.getAllAirports()).thenReturn(airports);

        ResponseEntity<List<Airport>> response = airportController.getAllAirports();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(airports, response.getBody());
    }

    @Test
    @DisplayName("Update Airport - Success")
    void testUpdateAirport() {
        Airport updated = new Airport();
        updated.setAirportId(1);
        updated.setAirportName("Updated Airport");
        updated.setCity("New City");
        updated.setContact("987654321");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        when(airportService.updateAirport(any(Airport.class), eq(1))).thenReturn(updated);

        ResponseEntity<Airport> response = airportController.updateAirport(1, updated, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());
    }

    @Test
    @DisplayName("Update Airport - Validation Failure")
    void testUpdateAirportValidationFails() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            airportController.updateAirport(1, sampleAirport, bindingResult);
        });

        assertEquals("Cannot update in Airport", exception.getMessage());
    }

    @Test
    @DisplayName("Delete Airport - Success")
    void testDeleteAirport() {
        doNothing().when(airportService).deleteAirport(1);

        ResponseEntity<Void> response = airportController.deleteAirport(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
