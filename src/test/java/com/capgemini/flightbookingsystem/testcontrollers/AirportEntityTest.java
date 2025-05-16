package com.capgemini.flightbookingsystem.testcontrollers;

import org.junit.jupiter.api.Test;

import com.capgemini.flightbookingsystem.entities.Airport;


import static org.junit.jupiter.api.Assertions.*;

public class AirportEntityTest {

	@Test
	public void testAirportFieldAssignment() {
		Airport airport = new Airport();
		airport.setAirportId(1);
		airport.setAirportName("Dubai International");
		airport.setCity("Dubai");
		airport.setContact(1234567890);

		assertEquals(1, airport.getAirportId());
		assertEquals("Dubai International", airport.getAirportName());
		assertEquals("Dubai", airport.getCity());
		assertEquals(1234567890, airport.getContact());

	}

	@Test
	public void testAirportNullOrBlankFields() {
		Airport airport = new Airport();

		airport.setCity(" ");
		airport.setContact(null);
		airport.setAirportName(null);

		assertEquals(" ", airport.getCity());
		assertNull(airport.getContact());
		assertNull(airport.getAirportName());
	}
}
