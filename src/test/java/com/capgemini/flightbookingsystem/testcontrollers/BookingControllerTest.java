package com.capgemini.flightbookingsystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.repositories.BookingRepository;
import com.capgemini.flightbookingsystem.services.BookingService;

public class BookingControllerTest {

	@Mock
	private BookingService bookingService;

	@InjectMocks
	private BookingController bookingController;

	private Booking booking1;
	private Booking booking2;

	@BeforeEach
	void setUpBooking() {
		MockitoAnnotations.openMocks(this);
		booking1 = new Booking(1, "A1", "Economy", LocalDateTime.now(), "Confirmed", 5000L, null, null);
		booking2 = new Booking(2, "B2", "Business", LocalDateTime.now(), "Pending", 10000L, null, null);
	}

	@Test
	@DisplayName("Should fetch number of bookings done")
	void testGetAllBookings() {
		List<Booking> bookings = Arrays.asList(booking1, booking2);
		when(bookingService.getAllBookings()).thenReturn(bookings);

		ResponseEntity<List<Booking>> response = bookingController.getAllBookings();

		assertEquals(200, response.getStatusCode().value());
		assertEquals(2, bookings.size());
	}

	@Test
	@DisplayName("Should add and return the added booking")
	void testAddBooking() {

		when(bookingService.saveBooking(booking1)).thenReturn(booking1);

		ResponseEntity<Booking> response = bookingController.addBooking(booking1);

		assertEquals(201, response.getStatusCode().value());
		assertEquals(booking1, response.getBody());
	}

	@Test
	@DisplayName("ID will be request and its corresponding entity will be response")
	void testGetBookingById() {
		when(bookingService.getBookingById(1)).thenReturn(booking1);

		ResponseEntity<Booking> response = bookingController.getBookingById(1);

		assertEquals(200, response.getStatusCode().value());
		assertEquals(booking1, response.getBody());
	}

	@Test
	@DisplayName("Updating the booking and updated will be shown")
	void testUpdateBooking() {
		Booking updatedBooking = new Booking(3, "A1", "Business", LocalDateTime.now(), "Confirmed", 6000L, null, null);
		when(bookingService.updateBooking(3, updatedBooking)).thenReturn(updatedBooking);

		ResponseEntity<Booking> response = bookingController.updateBooking(updatedBooking, 3);

		assertEquals(200, response.getStatusCode().value());
		assertEquals(updatedBooking, response.getBody());

	}

	@Test
	@DisplayName("Testing the delete method")
	public void testDeleteMethod() {
		doNothing().when(bookingService).deleteBooking(1);

		ResponseEntity<Booking> response = bookingController.deleteBooking(1);

		assertEquals(204, response.getStatusCode().value());
//		assertEquals("Booking deleted successfully", response.getBody());
	}
}
