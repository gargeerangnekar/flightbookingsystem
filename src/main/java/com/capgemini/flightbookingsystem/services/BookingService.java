package com.capgemini.flightbookingsystem.services;

import java.util.List;

import com.capgemini.flightbookingsystem.dto.BookingHistoryDto;
import com.capgemini.flightbookingsystem.dto.FlightBookingDto;
import com.capgemini.flightbookingsystem.entities.Booking;

//9
public interface BookingService {

	Booking saveBooking(Booking booking);

	Booking getBookingById(Integer bookingId);

	List<Booking> getAllBookings();

	Booking updateBooking(Integer bookingId, Booking booking);

	void deleteBooking(Integer bookingId);
	
	Booking patchBooking(Integer bookingId, Booking booking);
	
	List<FlightBookingDto> getAllFlights();

	List<BookingHistoryDto> getBookingHistoryById(Integer userId);
	
}
