package com.capgemini.flightbookingsystem.controllers;

import com.capgemini.flightbookingsystem.entities.Payments;
import com.capgemini.flightbookingsystem.services.PaymentsService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payments")
public class PaymentsController {

	private PaymentsService paymentsService;

	@Autowired
	public PaymentsController(PaymentsService paymentsService) {
		super();
		this.paymentsService = paymentsService;
	}

	@GetMapping
	public ResponseEntity<List<Payments>> getAllPayments() {
		return ResponseEntity.ok(paymentsService.getAllPayments());
	}

	@GetMapping("/{paymentId}")
	public ResponseEntity<Payments> getPaymentById(@PathVariable Integer paymentId) {
		return ResponseEntity.ok(paymentsService.getPaymentById(paymentId));
	}

	@PostMapping
	public ResponseEntity<Payments> createPayments(@RequestBody Payments payment) {
		Payments created = paymentsService.savePayments(payment);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{paymentId}")
	public ResponseEntity<Payments> updatePayments(@PathVariable Integer paymentId,
			@RequestBody Payments updatedPayment) {
		return ResponseEntity.ok(paymentsService.updatePayments(paymentId, updatedPayment));
	}

	@DeleteMapping("/{paymentId}")
	public ResponseEntity<Void> deletePayments(@PathVariable Integer paymentId) {
		paymentsService.deletePayments(paymentId);
		return ResponseEntity.noContent().build();
	}
}
