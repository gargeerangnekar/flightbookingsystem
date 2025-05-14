package com.capgemini.flightbookingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.flightbookingsystem.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
