package com.capgemini.flightbookingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.flightbookingsystem.entities.Flights;

//repository layer
@Repository
public interface FlightRepository extends JpaRepository<Flights, Long> {

}
