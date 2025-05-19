package com.capgemini.flightbookingsystem.services;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.flightbookingsystem.dto.BookingCardDTO;
import com.capgemini.flightbookingsystem.dto.BookingHistoryDto;
import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.entities.Flights;

//9
public interface BookingService {

	Booking saveBooking(Booking booking);

	Booking getBookingById(Integer bookingId);

	List<Booking> getAllBookings();

	Booking updateBooking(Integer bookingId, Booking booking);

	void deleteBooking(Integer bookingId);
	
	
	List<Flights> searchFlights(
			Integer departureAirportId, Integer arrivalAirportId , LocalDate departureTime);

	List<BookingHistoryDto> getBookingHistoryById(Integer userId);
	
	BookingCardDTO getBookingCardById(Integer bookingId);
	
	List<BookingHistoryDto> getAllBookingHistory();
	
}