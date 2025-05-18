package com.capgemini.flightbookingsystem.testcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.capgemini.flightbookingsystem.controllers.BookingController;
import com.capgemini.flightbookingsystem.dto.BookingHistoryDto;
import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.exceptions.BookingNotFoundException;
import com.capgemini.flightbookingsystem.services.BookingService;

class BookingControllerTest {

	@Mock
	private BookingService bookingService;

	@InjectMocks
	private BookingController bookingController;

	private Booking booking1;
	private Booking booking2;

	@BeforeEach
	void setUpBooking() {
		MockitoAnnotations.openMocks(this);
		booking1 = new Booking(1, "A1", "Economy", LocalDateTime.now(), 5000.0, null, null, null);
		booking2 = new Booking(2, "B2", "Business", LocalDateTime.now(), 10000.0, null, null, null);
	}

	@Test
	@DisplayName("Should fetch number of bookings done")
	void testGetAllBookings() {
		booking1 = new Booking(1, "A1", "Economy", LocalDateTime.now(), 5000.0, null, null, null);
		booking2 = new Booking(2, "B2", "Business", LocalDateTime.now(), 10000.0, null, null, null);
		List<Booking> bookings = Arrays.asList(booking1, booking2);
		when(bookingService.getAllBookings()).thenReturn(bookings);

		ResponseEntity<List<Booking>> response = bookingController.getAllBookings();

		assertEquals(200, response.getStatusCode().value());
		assertEquals(2, bookings.size());
	}

	@Test
	@DisplayName("Should add and return the added booking")
	void testAddBooking() {
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		when(bookingService.saveBooking(booking1)).thenReturn(booking1);

		ResponseEntity<Booking> response = bookingController.addBooking(booking1, bindingResult);

		assertEquals(201, response.getStatusCode().value());
		assertEquals(booking1, response.getBody());
	}

	@Test
	@DisplayName("ID will be request and its corresponding booking will be response")
	void testGetBookingById() {
		when(bookingService.getBookingById(1)).thenReturn(booking1);

		ResponseEntity<Booking> response = bookingController.getBookingById(1);

		assertEquals(200, response.getStatusCode().value());
		assertEquals(booking1, response.getBody());
	}

	@Test
	@DisplayName("Updating the booking and updated will be shown")
	void testUpdateBooking() {
		Booking updatedBooking = new Booking(3, "A1", "Business", LocalDateTime.now(), 6000.0, null, null, null);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bookingService.updateBooking(3, updatedBooking)).thenReturn(updatedBooking);

		ResponseEntity<Booking> response = bookingController.updateBooking(updatedBooking, 3, bindingResult);

		assertEquals(200, response.getStatusCode().value());
		assertEquals(updatedBooking, response.getBody());

	}

	@Test
	@DisplayName("Testing the delete method")
	void testDeleteMethod() {
		doNothing().when(bookingService).deleteBooking(1);

		ResponseEntity<Booking> response = bookingController.deleteBooking(1);

		assertEquals(204, response.getStatusCode().value());
	}

	@Test
	@DisplayName("Should throw the error for Booking not found exception")
	void testBookingNotFoundException() {
		when(bookingService.getBookingById(10))
				.thenThrow(new BookingNotFoundException("Booking not found with ID: " + 10));

		assertThrows(BookingNotFoundException.class, () -> bookingController.getBookingById(10));

	}

	@Test
	@DisplayName("Should throw IllegalArgumentException when adding booking with validation errors")
	void testAddBookingValidationFailure() {
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			bookingController.addBooking(booking1, bindingResult);
		});

		assertEquals("Cannot insert in Booking", thrown.getMessage());
	}
	@Test
	@DisplayName("Should fetch booking history for user")
	void testGetBookingHistory() {
		List<BookingHistoryDto> history = Arrays.asList(
				new BookingHistoryDto("John Doe", "Flight A123", LocalDateTime.now(), "12A",
						LocalDateTime.now().minusDays(1), "JFK", "LAX"));

		when(bookingService.getBookingHistoryById(1)).thenReturn(history);

		ResponseEntity<List<BookingHistoryDto>> response = bookingController.getBookingHistory(1);

		assertEquals(200, response.getStatusCode().value());
		assertEquals(1, response.getBody().size());
	}

	@Test
	@DisplayName("Should fetch empty booking history if none exists")
	void testGetEmptyBookingHistory() {
		when(bookingService.getBookingHistoryById(10)).thenReturn(Collections.emptyList());

		ResponseEntity<List<BookingHistoryDto>> response = bookingController.getBookingHistory(10);

		assertEquals(200, response.getStatusCode().value());
		assertEquals(0, response.getBody().size());
	}

}
