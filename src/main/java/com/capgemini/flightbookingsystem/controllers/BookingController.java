package com.capgemini.flightbookingsystem.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	BookingService bookingService;

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@GetMapping
	public ResponseEntity<List<Booking>> getAllBookings() {
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookings());
	}

	@PostMapping
	public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.saveBooking(booking));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable("id") Integer bookingId) {
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingById(bookingId));
	}

	@PutMapping("/update-booking/{id}")
	public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, @PathVariable("id") Integer bookingId) {
		return ResponseEntity.status(HttpStatus.OK).body(bookingService.updateBooking(bookingId, booking));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Booking> deleteBooking(@PathVariable("id") Integer bookingId) {
		bookingService.deleteBooking(bookingId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
