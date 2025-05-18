package com.capgemini.flightbookingsystem.controllers;

import com.capgemini.flightbookingsystem.entities.Payments;
import com.capgemini.flightbookingsystem.services.PaymentsService;

import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Valid;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payments")
public class PaymentsController {

    private final PaymentsService paymentsService;

    // Constructor injection
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public ResponseEntity<List<Payments>> getAllPayments() {
        log.info("GET /payments called");
        return ResponseEntity.ok(paymentsService.getAllPayments());
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payments> getPaymentById(@PathVariable Integer paymentId) {
        log.info("GET /payments/{} called", paymentId);
        return ResponseEntity.ok(paymentsService.getPaymentById(paymentId));
    }

    @PostMapping
    public ResponseEntity<Object> createPayments(@Valid @RequestBody Payments payment, BindingResult result) {
        log.info("POST /payments called with: {}", payment);
        if (result.hasErrors()) {
            log.warn("Validation failed: {}", result.getAllErrors());
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Payments created = paymentsService.savePayments(payment);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Object> updatePayments(
            @PathVariable Integer paymentId,
            @Valid @RequestBody Payments updatedPayment,
            BindingResult result) {
        log.info("PUT /payments/{} called with: {}", paymentId, updatedPayment);
        if (result.hasErrors()) {
            log.warn("Validation failed: {}", result.getAllErrors());
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(paymentsService.updatePayments(paymentId, updatedPayment));
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayments(@PathVariable Integer paymentId) {
        log.info("DELETE /payments/{} called", paymentId);
        paymentsService.deletePayments(paymentId);
        return ResponseEntity.noContent().build();
    }
}
