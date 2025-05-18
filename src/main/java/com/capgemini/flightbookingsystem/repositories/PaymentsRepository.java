package com.capgemini.flightbookingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capgemini.flightbookingsystem.entities.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

}