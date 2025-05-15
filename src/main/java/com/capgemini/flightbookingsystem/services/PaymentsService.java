package com.capgemini.flightbookingsystem.services;

import com.capgemini.flightbookingsystem.entities.Payments;

import java.util.List;

public interface PaymentsService {

	Payments getPaymentById(Integer paymentId);

	List<Payments> getAllPayments();

	Payments savePayments(Payments payment);

	Payments updatePayments(Integer paymentId, Payments user);

	void deletePayments(Integer paymentId);

}