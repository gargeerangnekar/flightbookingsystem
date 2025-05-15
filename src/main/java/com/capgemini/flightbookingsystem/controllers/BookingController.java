package com.capgemini.flightbookingsystem.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.services.BookingService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bookings")
@Slf4j
public class BookingController {

	BookingService bookingService;

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@GetMapping
	public ResponseEntity<List<Booking>> getAllBookings() {
		log.info("Request received to fetch all bookings");
		List<Booking> bookings = bookingService.getAllBookings();
		log.debug("Returning {} bookings ", bookings.size());
		return ResponseEntity.ok(bookings);

	}

	@PostMapping
	public ResponseEntity<Booking> addBooking(@Valid @RequestBody Booking booking, BindingResult result) {
		log.info("Request received to create booking: {}", booking);
		Booking saveBooking = bookingService.saveBooking(booking);
		log.debug("Booking created with ID: {}", saveBooking.getBookingId());

		URI location = URI.create("/bookings/" + saveBooking.getBookingId());
		return ResponseEntity.created(location).body(saveBooking);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable("id") Integer bookingId) {
		log.info("Request recieved to get booking with ID: ", bookingId);
		Booking booking = bookingService.getBookingById(bookingId);
		log.debug("Booking fethced :{}", booking);
		return ResponseEntity.ok(booking);
	}

	@PutMapping("/update-booking/{id}")
	public ResponseEntity<Booking> updateBooking(@Valid @RequestBody Booking booking,
			@PathVariable("id") Integer bookingId) {
		log.info("Request received to update booking for ID: ", bookingId);
		Booking updatedBooking = bookingService.updateBooking(bookingId, booking);
		log.debug("Booking updated for ID: ", updatedBooking.getBookingId());
		return ResponseEntity.ok(updatedBooking);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Booking> deleteBooking(@PathVariable("id") Integer bookingId) {
		log.info("Booking deleted with ID :", bookingId);
		bookingService.deleteBooking(bookingId);
		log.info("Booking deleted successfully for ID: ", bookingId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

}
