package com.capgemini.flightbookingsystem.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.entities.Payments;
import com.capgemini.flightbookingsystem.entities.User;
import com.capgemini.flightbookingsystem.exceptions.BookingNotFoundException;
import com.capgemini.flightbookingsystem.exceptions.UserNotFoundException;
import com.capgemini.flightbookingsystem.repositories.BookingRepository;
import com.capgemini.flightbookingsystem.repositories.FlightRepository;
import com.capgemini.flightbookingsystem.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

	BookingRepository bookingRepository;
	UserRepository userRepository;
	FlightRepository flightRepository;

	public BookingServiceImpl(BookingRepository bookingRepository, FlightRepository flightRepository,
			UserRepository userRepository) {
		this.bookingRepository = bookingRepository;
		this.userRepository = userRepository;
		this.flightRepository = flightRepository;
	}

	@Override
	public void deleteBooking(Integer bookingId) {
		log.debug("Deleting booking with ID :", bookingId);
		Booking newBooking = bookingRepository.findById(bookingId).orElseThrow(() -> {
			log.warn("Booking not found with ID :", bookingId);
			return new BookingNotFoundException("Booking not found with ID:" + bookingId);
		});
		log.info("Booking deleted successfully with ID: ", bookingId);
		bookingRepository.delete(newBooking);
	}

	@Override
	public List<Booking> getAllBookings() {
		log.debug("Fetching all bookings from repository");
		return bookingRepository.findAll();
	}

	@Override
	public Booking saveBooking(Booking booking) {
		log.debug("Creating new booking and saving in repository");
		if (booking.getUsers() != null && booking.getUsers().getUserId() != null) {
			User user = userRepository.findById(booking.getUsers().getUserId())
					.orElseThrow(() -> new UserNotFoundException("User not found"));
			booking.setUsers(user);
		}
		if (booking.getFlights() != null && booking.getFlights().getFlightId() != null) {
			Flights flight = flightRepository.findById(booking.getFlights().getFlightId())
					.orElseThrow(() -> new RuntimeException("Flight not found"));
			booking.setFlights(flight);
		}

		Payments payment = new Payments();
		payment.setAmount(booking.getAmount());
		payment.setPaymentDatetime(LocalDateTime.now());

		booking.setPayment(payment);

		return bookingRepository.save(booking);
	}

	@Override
	public Booking updateBooking(Integer bookingId, Booking booking) {
		log.debug("Updating Booking with ID: {}", bookingId);

		Booking existing = bookingRepository.findById(bookingId).orElseThrow(() -> {
			log.warn("Booking not found with ID: {}", bookingId);
			return new BookingNotFoundException("Booking not found with ID: " + bookingId);
		});

		existing.setAmount(booking.getAmount());
		existing.setBookingTime(booking.getBookingTime());
		existing.setSeatClass(booking.getSeatClass());
		existing.setSeatNumber(booking.getSeatNumber());
		existing.setFlights(booking.getFlights());
		existing.setUsers(booking.getUsers());

		Booking updated = bookingRepository.save(existing);
		log.info("Booking updated successfully with ID: {}", bookingId);
		return updated;
	}

	@Override
	public Booking getBookingById(Integer bookingId) {
		log.debug("Fetching booking with ID: ", bookingId);
		return bookingRepository.findById(bookingId).orElseThrow(() -> {
			log.warn("Booking not found with ID ", bookingId);
			return new BookingNotFoundException("Booking not found with ID :" + bookingId);
		});
	}

	@Override
	public Booking patchBooking(Integer bookingId, Booking booking) {
		log.info("Patching boking with ID: {}", bookingId);
		Booking existing = bookingRepository.findById(bookingId).orElseThrow(() -> {
			log.warn("booking not found for patch with ID: {}", bookingId);
			return new BookingNotFoundException("Patch :Booking not found with Id : " + bookingId);
		});
		if (booking.getAmount() != null) {
			existing.setAmount(booking.getAmount());
			log.debug("Updated amount to: {}", booking.getAmount());
		}

		if (booking.getSeatNumber() != null) {
			existing.setSeatNumber(booking.getSeatNumber());
			log.debug("Updated seat number to: {}", booking.getSeatNumber());
		}
		if (booking.getSeatClass() != null) {
			existing.setSeatClass(booking.getSeatClass());
			log.debug("Updated seat class to: {}", booking.getSeatClass());
		}
		if (booking.getBookingTime() != null) {
			existing.setBookingTime(booking.getBookingTime());
			log.debug("Updated booking time to: {}", booking.getBookingTime());
		}
		if (booking.getUsers() != null) {
			existing.setUsers(booking.getUsers());
			log.debug("Updated user to: {}", booking.getUsers().getUserId());
		}
		if (booking.getFlights() != null) {
			existing.setFlights(booking.getFlights());
			log.debug("Updated flight to: {}", booking.getFlights().getFlightId());
		}

		Booking updated = bookingRepository.save(existing);
		log.debug("booking with ID {} patched successfully", bookingId);
		return updated;
	}
}
