package com.capgemini.flightbookingsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.flightbookingsystem.dto.FlightBookingDto;
import com.capgemini.flightbookingsystem.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	@Query(value = "SELECT new com.capgemini.flightbookingsystem.dto.FlightBookingDto("
		     + "f.departure_airport_id, f.arrival_airport_id, "
		     + "dep.airport_name, arr.airport_name, "
		     + "f.departure_time, f.arrival_time, f.amount) "
		     + "FROM flights f "
		     + "JOIN airport dep ON f.departure_airport_id = dep.airport_id "
		     + "JOIN airport arr ON f.arrival_airport_id = arr.airport_id", nativeQuery = true)
		List<FlightBookingDto> getALLBookingDto();


}
