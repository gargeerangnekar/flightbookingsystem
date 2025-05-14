package com.capgemini.flightbookingsystem.services;

import com.capgemini.flightbookingsystem.entities.Payments;

import java.util.List;

public interface PaymentsService{

	Payments getPaymentById(Long paymentId);

	List<Payments> getAllPayments();

	Payments updatePayments(Long paymentId, Payments user);

	void deletePayments(Long paymentId);
}