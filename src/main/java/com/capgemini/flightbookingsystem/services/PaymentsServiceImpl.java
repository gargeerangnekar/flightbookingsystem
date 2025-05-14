package com.capgemini.flightbookingsystem.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.flightbookingsystem.entities.Payments;
import com.capgemini.flightbookingsystem.repositories.PaymentsRepository;


public class PaymentsServiceImpl implements PaymentsService {
	
	private PaymentsRepository paymentRepo;

	@Autowired
	public PaymentsServiceImpl(PaymentsRepository paymentRepo) {
		this.paymentRepo = paymentRepo;
	}

	@Override
	public Payments getPaymentById(Long paymentId) {
		return paymentRepo.findById(paymentId)
        		.orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));
	}

	@Override
	public List<Payments> getAllPayments() {
		 return paymentRepo.findAll();
	}

	@Override
	public Payments updatePayments(Long paymentId, Payments paymentUpdates) {
	    Payments existingPayment = paymentRepo.findById(paymentId)
	        .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));
	    
	    if (paymentUpdates.getAmount() != null) {
	        existingPayment.setAmount(paymentUpdates.getAmount());
	    }
	    
	    if (paymentUpdates.getPaymentMethod() != null) {
	        existingPayment.setPaymentMethod(paymentUpdates.getPaymentMethod());
	    }
	    
	    if (paymentUpdates.getPaymentDate() != null) {
	        existingPayment.setPaymentDate(paymentUpdates.getPaymentDate());
	    }
	    
	    if (paymentUpdates.getPaymentTime() != null) {
	        existingPayment.setPaymentTime(paymentUpdates.getPaymentTime());
	    }
	    
	    return paymentRepo.save(existingPayment);
	}

	@Override
	public void deletePayments(Long paymentId) {
		 if (!paymentRepo.existsById(paymentId)) {
	            throw new RuntimeException("User not found with ID: " + paymentId);
	        }
		 paymentRepo.deleteById(paymentId);
		
	}

}
