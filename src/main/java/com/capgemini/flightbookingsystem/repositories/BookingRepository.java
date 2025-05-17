package com.capgemini.flightbookingsystem.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.flightbookingsystem.dto.BookingHistoryDto;
import com.capgemini.flightbookingsystem.dto.FlightBookingDto;
import com.capgemini.flightbookingsystem.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	@Query("SELECT new com.capgemini.flightbookingsystem.dto.FlightBookingDto(" +
		       "f.departureAirportId, f.arrivalAirportId, " +
		       "dep.airportName, arr.airportName, "
		       + "dep.city, arr.city, " +
		       "f.departureTime, f.arrivalTime, f.amount) " +
		       "FROM Flights f " +
		       "JOIN Airport dep ON f.departureAirportId = dep.airportId " +
		       "JOIN Airport arr ON f.arrivalAirportId = arr.airportId ")
		List<FlightBookingDto> getAllBookingDtoForDisplay();
	
	@Query("SELECT new com.capgemini.flightbookingsystem.dto.FlightBookingDto(" +
		       "f.departureAirportId, f.arrivalAirportId, " +
		       "dep.airportName, arr.airportName, "
		       + "dep.city, arr.city, " +
		       "f.departureTime, f.arrivalTime, f.amount) " +
		       "FROM Flights f " +
		       "JOIN Airport dep ON f.departureAirportId = dep.airportId " +
		       "JOIN Airport arr ON f.arrivalAirportId = arr.airportId "+
		       "WHERE DATE(f.departureTime) = ?1 "
		       + "AND dep.city = :departureCity "
		       + "AND arr.city = :arrivalCity") 
		List<FlightBookingDto> getAllBookingDto(LocalDateTime departureTime,
				@Param("departureCity") String departureCity,
			    @Param("arrivalCity") String arrivalCity);
	
	
	@Query("SELECT new com.capgemini.flightbookingsystem.dto.BookingHistoryDto( " +
		       "u.name, f.flightNumber, f.arrivalTime, " +
		       "b.seatNumber, b.bookingTime, " +
		       "dep.airportName, arr.airportName) " +
		       "FROM Booking b " +
		       "JOIN User u ON b.users.userId = u.userId " +
		       "JOIN Flights f ON b.flights.flightId = f.flightId " +
		       "JOIN Airport dep ON f.departureAirportId = dep.airportId " +
		       "JOIN Airport arr ON f.arrivalAirportId = arr.airportId " +
		       "WHERE u.userId = ?1")
		List<BookingHistoryDto> getBookingHistory(Integer userId);



}