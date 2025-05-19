package com.capgemini.flightbookingsystem.controllers;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.flightbookingsystem.dto.BookingCardDTO;
import com.capgemini.flightbookingsystem.dto.BookingHistoryDto;
import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.entities.Flights;
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

		if (result.hasErrors()) {
			log.warn("Validation failed for Booking {}:", result.getAllErrors());
			throw new IllegalArgumentException("Cannot insert in Booking");
		}

		Booking saveBooking = bookingService.saveBooking(booking);
		log.debug("Booking created with ID: {}", saveBooking.getBookingId());

		return ResponseEntity.created(URI.create("/bookings/" + saveBooking.getBookingId())).body(saveBooking);
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") Integer bookingId) {
		log.info("Request recieved to get booking with ID: ", bookingId);
		Booking booking = bookingService.getBookingById(bookingId);
		log.debug("Booking fethced :{}", booking);
		return ResponseEntity.ok(booking);
	}

	@PutMapping("/{bookingId}")
	public ResponseEntity<Booking> updateBooking(@Valid @RequestBody Booking booking,
			@PathVariable("bookingId") Integer bookingId, BindingResult result) {
		log.info("Request received to update booking for ID: ", bookingId);

		if (result.hasErrors()) {
			log.warn("Validation failed {}:", result.getAllErrors());
			throw new IllegalArgumentException("Cannot update Booking");
		}

		Booking updatedBooking = bookingService.updateBooking(bookingId, booking);
		log.debug("Booking updated for ID: ", updatedBooking.getBookingId());
		return ResponseEntity.ok(updatedBooking);
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<Booking> deleteBooking(@PathVariable("bookingId") Integer bookingId) {
		log.info("Booking deleted with ID :", bookingId);
		bookingService.deleteBooking(bookingId);
		log.info("Booking deleted successfully for ID: ", bookingId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


	@GetMapping("/history/{userId}")
	public ResponseEntity<List<BookingHistoryDto>> getBookingHistory(@PathVariable Integer userId) {
		log.info("Request to fetch booking history for ID :", userId);
		List<BookingHistoryDto> bookingHistory = bookingService.getBookingHistoryById(userId);
		log.info("Booking history fetched for ID :", userId);
		return ResponseEntity.status(HttpStatus.OK).body(bookingHistory);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Flights>> searchFlights(@RequestParam Integer fromCity, @RequestParam Integer toCity,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
		log.info("Searching flights for input data");
		List<Flights> flights = bookingService.searchFlights(fromCity, toCity, departureDate);
		log.info("Fetched results");
		return ResponseEntity.status(HttpStatus.OK).body(flights);
	}
	
	@GetMapping("/view/{bookingId}")
	public ResponseEntity<BookingCardDTO> getBookingDetails(@PathVariable Integer bookingId) {
	    log.info("Fetching booking details for display card: {}", bookingId);
	    BookingCardDTO booking = bookingService.getBookingCardById(bookingId);
	    return ResponseEntity.ok(booking);
	}


}