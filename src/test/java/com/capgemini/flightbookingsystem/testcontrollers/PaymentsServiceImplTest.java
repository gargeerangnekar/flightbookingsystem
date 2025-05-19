package com.capgemini.flightbookingsystem.testcontrollers;


import com.capgemini.flightbookingsystem.entities.Payments;
import com.capgemini.flightbookingsystem.repositories.PaymentsRepository;
import com.capgemini.flightbookingsystem.services.PaymentsServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentsServiceImplTest {

    @Mock
    private PaymentsRepository paymentsRepository;

    @InjectMocks
    private PaymentsServiceImpl paymentsService;

    private Payments samplePayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        samplePayment = new Payments();
        samplePayment.setPaymentId(1);
        samplePayment.setAmount(5000.0);
        samplePayment.setPaymentDatetime(LocalDateTime.now());
    }

    @Test
    void testGetAllPayments() {
        when(paymentsRepository.findAll()).thenReturn(List.of(samplePayment));

        List<Payments> result = paymentsService.getAllPayments();

        assertEquals(1, result.size());
        assertEquals(samplePayment, result.get(0));
    }

    @Test
    void testGetPaymentById() {
        when(paymentsRepository.findById(1)).thenReturn(Optional.of(samplePayment));

        Payments result = paymentsService.getPaymentById(1);

        assertEquals(samplePayment, result);
    }

    @Test
    void testSavePayments() {
        when(paymentsRepository.save(samplePayment)).thenReturn(samplePayment);

        Payments result = paymentsService.savePayments(samplePayment);

        assertEquals(samplePayment, result);
    }

    @Test
    void testUpdatePayments() {
        when(paymentsRepository.findById(1)).thenReturn(Optional.of(samplePayment));
        when(paymentsRepository.save(samplePayment)).thenReturn(samplePayment);

        Payments result = paymentsService.updatePayments(1, samplePayment);

        assertEquals(samplePayment, result);
    }

    @Test
    void testDeletePayments() {
        doNothing().when(paymentsRepository).deleteById(1);

        assertDoesNotThrow(() -> paymentsService.deletePayments(1));
        verify(paymentsRepository, times(1)).deleteById(1);
    }
}
