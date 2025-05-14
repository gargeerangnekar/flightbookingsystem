package com.capgemini.flightbookingsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.flightbookingsystem.entities.Payments;
import com.capgemini.flightbookingsystem.services.PaymentsService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PaymentsController {

	private final PaymentsService paymentService;

	@Autowired
	public PaymentsController(PaymentsService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	@GetMapping("/{paymentId}")
	public ResponseEntity<Payments> getUser(@PathVariable Long paymentId) {
		return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
	}

	@GetMapping
	public ResponseEntity<List<Payments>> getAllUsers() {
		return ResponseEntity.ok(paymentService.getAllPayments());
	}

	@PutMapping("/{paymentId}")
	public ResponseEntity<Payments> updateUser(@PathVariable Long paymentId, @RequestBody Payments payment) {
		return ResponseEntity.ok(paymentService.updatePayments(paymentId, payment));
	}

	@DeleteMapping("/{paymentId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long paymentId) {
		paymentService.deletePayments(paymentId);
		return ResponseEntity.noContent().build();
	}
}
