package com.capgemini.flightbookingsystem.testcontrollers;

import com.capgemini.flightbookingsystem.controllers.PaymentsController;
import com.capgemini.flightbookingsystem.entities.Payments;
import com.capgemini.flightbookingsystem.services.PaymentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentsControllerTest {

    @Mock
    private PaymentsService paymentsService;

    @InjectMocks
    private PaymentsController paymentsController;

    private Payments samplePayment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        samplePayment = new Payments();
        samplePayment.setPaymentId(1);
        samplePayment.setAmount(12500.0);
        samplePayment.setPaymentDatetime(LocalDateTime.of(2025, 6, 1, 15, 0));
        samplePayment.setBookingId(10);
        samplePayment.setUserId(1);
    }

    @Test
    public void testGetAllPayments() {
        when(paymentsService.getAllPayments()).thenReturn(List.of(samplePayment));

        ResponseEntity<List<Payments>> response = paymentsController.getAllPayments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(samplePayment, response.getBody().get(0));
    }

    @Test
    public void testGetPaymentById() {
        when(paymentsService.getPaymentById(1)).thenReturn(samplePayment);

        ResponseEntity<Payments> response = paymentsController.getPaymentById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(samplePayment, response.getBody());
    }

    @Test
    public void testSavePayments() {
        when(paymentsService.savePayments(samplePayment)).thenReturn(samplePayment);

        ResponseEntity<Payments> response = paymentsController.createPayments(samplePayment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(samplePayment, response.getBody());
    }

    @Test
    public void testUpdatePayments() {
        when(paymentsService.updatePayments(eq(1), any(Payments.class))).thenReturn(samplePayment);

        ResponseEntity<Payments> response = paymentsController.updatePayments(1, samplePayment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(samplePayment, response.getBody());
    }

    @Test
    public void testDeletePayments() {
        doNothing().when(paymentsService).deletePayments(1);

        ResponseEntity<Void> response = paymentsController.deletePayments(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(paymentsService, times(1)).deletePayments(1);
    }
}
