package com.capgemini.flightbookingsystem.services;

import java.util.List;

import com.capgemini.flightbookingsystem.entities.Booking;

public interface BookingService {

	Booking saveBooking(Booking booking);

	Booking getBookingById(Integer bookingId);

	List<Booking> getAllBookings();

	Booking updateBooking(Integer bookingId, Booking booking);

	void deleteBooking(Integer bookingId);

}
