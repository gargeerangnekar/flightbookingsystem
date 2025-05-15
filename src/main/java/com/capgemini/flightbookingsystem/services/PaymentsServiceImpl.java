package com.capgemini.flightbookingsystem.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.Payments;
import com.capgemini.flightbookingsystem.exceptions.PaymentNotFoundException;
import com.capgemini.flightbookingsystem.repositories.PaymentsRepository;

//13
@Service
public class PaymentsServiceImpl implements PaymentsService {

    private final PaymentsRepository paymentRepo;

    @Autowired
    public PaymentsServiceImpl(PaymentsRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @Override
    public Payments getPaymentById(Integer paymentId) {
        return paymentRepo.findById(paymentId)
            .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + paymentId));
    }

    @Override
    public List<Payments> getAllPayments() {
        return paymentRepo.findAll();
    }

    @Override
    public Payments savePayments(Payments payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public Payments updatePayments(Integer paymentId, Payments paymentUpdates) {
        Payments existing = paymentRepo.findById(paymentId)
            .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + paymentId));

        if (paymentUpdates.getAmount() != null) {
            existing.setAmount(paymentUpdates.getAmount());
        }
        if (paymentUpdates.getPaymentDatetime() != null) {
            existing.setPaymentDatetime(paymentUpdates.getPaymentDatetime());
        }

        return paymentRepo.save(existing);
    }

    @Override
    public void deletePayments(Integer paymentId) {
        if (!paymentRepo.existsById(paymentId)) {
            throw new PaymentNotFoundException("Payment not found with ID: " + paymentId);
        }
        paymentRepo.deleteById(paymentId);
    }
}
