package com.capgemini.flightbookingsystem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.repositories.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	BookingRepository bookingRepository;

	public BookingServiceImpl(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@Override
	public void deleteBooking(Integer bookingId) {
		Booking newBooking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("Booking not found with ID :" + bookingId));
		bookingRepository.delete(newBooking);
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public Booking updateBooking(Integer bookingId, Booking booking) {
		Booking existing = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("Booking not found with ID :" + bookingId));

		existing.setAmount(booking.getAmount());
		existing.setBookingTime(booking.getBookingTime());
		existing.setSeatClass(booking.getSeatClass());
		existing.setSeatNumber(booking.getSeatNumber());
		existing.setStatus(booking.getStatus());
		existing.setFlights(booking.getFlights());
		existing.setUsers(booking.getUsers());

		return bookingRepository.save(existing);

	}

	@Override
	public Booking getBookingById(Integer bookingId) {
		return bookingRepository.findById(bookingId).orElseThrow();
	}

}
