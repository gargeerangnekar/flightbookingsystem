package com.capgemini.flightbookingsystem.services;

import java.time.LocalDateTime;
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
	
	
	List<FlightBookingDto> getAllFlightsForDisplay();
	
	List<FlightBookingDto> getAllFlights(LocalDateTime departureTime,
			String departureCity, String arrivalCity);

	List<BookingHistoryDto> getBookingHistoryById(Integer userId);
	
}