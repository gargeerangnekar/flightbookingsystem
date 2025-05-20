package com.capgemini.flightbookingsystem.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Reusable error response builder
    private Map<String, Object> buildErrorDetails(String message, int status, String uri) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("status", status);
        errorDetails.put("message", message);
        errorDetails.put("instance", uri);
        return errorDetails;
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<Object> handleBookingNotFound(BookingNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Object> handleFlightNotFound(FlightNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AirlineAdminNotFoundException.class)
    public ResponseEntity<Object> handleAirlineAdminNotFound(AirlineAdminNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExist.class)
    public ResponseEntity<Object> handleEmailAlreadyExist(EmailAlreadyExist ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.CONFLICT.value(), request.getRequestURI()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<Object> handleAirportNotFound(AirportNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Object> handlePaymentNotFound(PaymentNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<Object> handleEmailNotFound(EmailNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI()), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequest(InvalidRequestException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    // Catch-all for unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorDetails(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        String errorMessage;
        var fieldError = ex.getBindingResult().getFieldError();
        if (fieldError != null && fieldError.getDefaultMessage() != null) {
            errorMessage = fieldError.getDefaultMessage();
        } else {
            errorMessage = "Validation error";
        }

        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();

        return new ResponseEntity<>(buildErrorDetails(errorMessage, HttpStatus.BAD_REQUEST.value(), uri), HttpStatus.BAD_REQUEST);
    }
}