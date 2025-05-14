package com.capgemini.flightbookingsystem.testcontrollers;

import com.capgemini.flightbookingsystem.controllers.AirLineAdminController;
import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.services.AirLineAdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirLineAdminControllerTest {

	@Mock
	private AirLineAdminService airLineAdminService;

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
	}

	@Test
	void testGetAllAdmins() {
		List<AirLineAdmin> admins = List.of(sampleAdmin);
		when(airLineAdminService.getAllAirlineAdmins()).thenReturn(admins);

		ResponseEntity<List<AirLineAdmin>> response = airLineAdminController.getAllAdmins();

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(1, response.getBody().size());
		assertEquals(sampleAdmin, response.getBody().get(0));
	}

	@Test
	void testGetAdminById() {
		when(airLineAdminService.getAirlineAdminById(1)).thenReturn(sampleAdmin);

		ResponseEntity<AirLineAdmin> response = airLineAdminController.getAdminById(1);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(sampleAdmin, response.getBody());
	}

	@Test
	void testCreateAdmin() {
		when(airLineAdminService.createAdmin(sampleAdmin)).thenReturn(sampleAdmin);

		ResponseEntity<AirLineAdmin> response = airLineAdminController.createAdmin(sampleAdmin);

		assertEquals(201, response.getStatusCodeValue());
		assertEquals(sampleAdmin, response.getBody());
	}

	@Test
	void testUpdateAdmin() {
		when(airLineAdminService.updateAdmin(eq(1), any(AirLineAdmin.class))).thenReturn(sampleAdmin);

		ResponseEntity<AirLineAdmin> response = airLineAdminController.updateAdmin(1, sampleAdmin);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(sampleAdmin, response.getBody());
	}

	@Test
	void testDeleteAdmin() {
		doNothing().when(airLineAdminService).deleteAdmin(1);

		ResponseEntity<Void> response = airLineAdminController.deleteAdmin(1);

		assertEquals(204, response.getStatusCodeValue());

		assertNull(response.getBody());
	}

	@Test
	void testCheckEmailExists() {
		when(airLineAdminService.existsByAirlineEmail("john@example.com")).thenReturn(true);

		ResponseEntity<Map<String, Boolean>> response = airLineAdminController.checkEmailExists("john@example.com");

		assertEquals(200, response.getStatusCodeValue());
		assertTrue(response.getBody().get("exists"));
	}

	@Test
	void testCheckContactExists() {
		when(airLineAdminService.existsByContactNumber("1234567890")).thenReturn(true);

		ResponseEntity<Map<String, Boolean>> response = airLineAdminController.checkContactExists("1234567890");

		assertEquals(200, response.getStatusCodeValue());
		assertTrue(response.getBody().get("exists"));
	}
}
