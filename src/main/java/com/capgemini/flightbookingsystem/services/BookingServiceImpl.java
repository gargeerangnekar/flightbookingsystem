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
	public Booking updateBooking(Long bookingId, Booking booking) {
		Booking existing = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new RuntimeException("Booking not found with ID :" + bookingId));

		existing.setBookingDate(booking.getBookingDate());
		existing.setAmount(booking.getAmount());
		existing.setBookingTime(booking.getBookingTime());
		existing.setFlightId(booking.getFlightId());
		existing.setSeatClass(booking.getSeatClass());
		existing.setSeatNumber(booking.getSeatNumber());
		existing.setStatus(booking.getStatus());
		existing.setUserId(booking.getUserId());

		return bookingRepository.save(existing);

	}

	@Override
	public Booking getBookingById(Long bookingId) {
		return bookingRepository.findById(bookingId).orElseThrow();
	}

}
