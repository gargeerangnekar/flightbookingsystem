package com.capgemini.flightbookingsystem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.exceptions.BookingNotFoundException;
import com.capgemini.flightbookingsystem.repositories.BookingRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

	BookingRepository bookingRepository;

	public BookingServiceImpl(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
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
		existing.setStatus(booking.getStatus());
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
}
