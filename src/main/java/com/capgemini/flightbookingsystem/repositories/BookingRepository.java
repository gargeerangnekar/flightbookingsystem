package com.capgemini.flightbookingsystem.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.flightbookingsystem.dto.BookingHistoryDto;
import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.entities.Flights;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	

	@Query("""
		    SELECT f
		    FROM Flights f
		    WHERE f.departureAirportId = :departureAirportId
		      AND f.arrivalAirportId = :arrivalAirportId
		      AND DATE(f.departureTime) = :departureDate
		      AND f.displayStatus = 'available'
		""")
		List<Flights> searchFlights(
		    @Param("departureAirportId") Integer departureAirportId,
		    @Param("arrivalAirportId") Integer arrivalAirportId,
		    @Param("departureDate") LocalDate departureDate
		);

	
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

	 List<Booking> findByBookingTimeBetween(LocalDateTime start, LocalDateTime end);
	 

}