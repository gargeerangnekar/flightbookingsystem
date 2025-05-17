package com.capgemini.flightbookingsystem.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.flightbookingsystem.entities.Flights;

//repository layer
@Repository
public interface FlightRepository extends JpaRepository<Flights, Integer> {
	@Query("SELECT f FROM Flights f JOIN f.bookings b WHERE b.bookingTime BETWEEN :start AND :end")
	List<Flights> findFlightsWithBookingsBetweenDates(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


}
