package com.capgemini.flightbookingsystem.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.flightbookingsystem.entities.Payments;


public interface PaymentsRepository extends JpaRepository<Payments, Long> {

    @Query("SELECT p FROM Payments p JOIN FETCH p.user u WHERE p.userId = :userId")
    List<Payments> findByUserIdWithUserDetails(@Param("userId") Long userId);

    @Query("SELECT p FROM Payments p JOIN FETCH p.booking b WHERE p.bookingId = :bookingId")
    List<Payments> findByBookingIdWithBookingDetails(@Param("bookingId") Long bookingId);
    
    @Query("SELECT p FROM Payments p JOIN FETCH p.booking b WHERE p.paymentId = :paymentId")
    Payments findByIdWithBookingDetails(@Param("paymentId") Long paymentId);
	
}