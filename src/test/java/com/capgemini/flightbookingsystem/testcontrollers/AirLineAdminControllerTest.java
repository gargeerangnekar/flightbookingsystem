package com.capgemini.flightbookingsystem.testcontrollers;

import com.capgemini.flightbookingsystem.controllers.AirLineAdminController;
import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.exceptions.AirlineAdminNotFoundException;
import com.capgemini.flightbookingsystem.services.AirLineAdminService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirLineAdminControllerTest {

    @Mock
    private AirLineAdminService airLineAdminService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AirLineAdminController airLineAdminController;

    private AirLineAdmin sampleAdmin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleAdmin = new AirLineAdmin();
        sampleAdmin.setAirlineAdminId(1);
        sampleAdmin.setAirlineAdminName("John Doe");
        sampleAdmin.setAirlineEmail("john@example.com");
        sampleAdmin.setContactNumber("1234567890");
        sampleAdmin.setPassword("securePass");
    }

    @Test
    void testGetAllAdmins() {
        List<AirLineAdmin> admins = List.of(sampleAdmin);
        when(airLineAdminService.getAllAirlineAdmins()).thenReturn(admins);

        ResponseEntity<List<AirLineAdmin>> response = airLineAdminController.getAllAdmins();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(sampleAdmin, response.getBody().get(0));
    }

    @Test
    void testGetAdminById() {
        when(airLineAdminService.getAirlineAdminById(1)).thenReturn(sampleAdmin);

        ResponseEntity<AirLineAdmin> response = airLineAdminController.getAdminById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(sampleAdmin, response.getBody());
    }

    @Test
    void testGetAdminById_NotFound() {
        when(airLineAdminService.getAirlineAdminById(999))
                .thenThrow(new AirlineAdminNotFoundException("Admin not found with ID: 999"));

        AirlineAdminNotFoundException exception = assertThrows(AirlineAdminNotFoundException.class, () -> {
            airLineAdminController.getAdminById(999);
        });

        assertEquals("Admin not found with ID: 999", exception.getMessage());
    }

   
    @Test
    void testCreateAdmin_ValidationErrors() {
        // Arrange
        when(bindingResult.hasErrors()).thenReturn(true);

        ObjectError mockError = mock(ObjectError.class);
        when(mockError.getDefaultMessage()).thenReturn("Invalid email");

        when(bindingResult.getAllErrors()).thenReturn(List.of(mockError));

        // Act
        ResponseEntity<Object> response = airLineAdminController.createAdmin(sampleAdmin, bindingResult);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);

        List<?> errors = (List<?>) response.getBody();
        assertTrue(errors.contains("Invalid email"));  // This should now pass
    }


    @Test
    void testUpdateAdmin_Success() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(airLineAdminService.updateAdmin(eq(1), any(AirLineAdmin.class))).thenReturn(sampleAdmin);

        ResponseEntity<Object> response = airLineAdminController.updateAdmin(1, sampleAdmin, bindingResult);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleAdmin, response.getBody());
    }

    @Test
    void testUpdateAdmin_ValidationErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(List.of(new ObjectError("admin", "Invalid contact number")));

        ResponseEntity<Object> response = airLineAdminController.updateAdmin(1, sampleAdmin, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        List<?> errors = (List<?>) response.getBody();
        assertTrue(errors.contains("Invalid contact number"));
    }

    @Test
    void testPatchAdmin_Success() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("airlineAdminName", "Jane Doe");

        AirLineAdmin updatedAdmin = new AirLineAdmin();
        updatedAdmin.setAirlineAdminId(1);
        updatedAdmin.setAirlineAdminName("Jane Doe");

        when(airLineAdminService.patchAdmin(1, updates)).thenReturn(updatedAdmin);

        ResponseEntity<AirLineAdmin> response = airLineAdminController.patchAdmin(1, updates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jane Doe", response.getBody().getAirlineAdminName());
    }

    @Test
    void testDeleteAdmin_Success() {
        doNothing().when(airLineAdminService).deleteAdmin(1);

        ResponseEntity<Void> response = airLineAdminController.deleteAdmin(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteAdmin_NotFound() {
        doThrow(new AirlineAdminNotFoundException("Admin not found with ID: 2"))
                .when(airLineAdminService).deleteAdmin(2);

        AirlineAdminNotFoundException exception = assertThrows(AirlineAdminNotFoundException.class, () -> {
            airLineAdminController.deleteAdmin(2);
        });

        assertEquals("Admin not found with ID: 2", exception.getMessage());
    }

    @Test
    void testCheckEmailExists() {
        when(airLineAdminService.existsByAirlineEmail("john@example.com")).thenReturn(true);

        ResponseEntity<Map<String, Boolean>> response = airLineAdminController.checkEmailExists("john@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().get("exists"));
    }

    @Test
    void testCheckContactExists() {
        when(airLineAdminService.existsByContactNumber("1234567890")).thenReturn(true);

        ResponseEntity<Map<String, Boolean>> response = airLineAdminController.checkContactExists("1234567890");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().get("exists"));
    }
}
