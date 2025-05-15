package com.capgemini.flightbookingsystem.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final String TIMESTAMP = "Timestamp :";
	private static final String STATUS = "Status :";
	private static final String DESCRIPTION = "Description :";
	private static final String INSTANCE = "Instance :";

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Object> handleBookingNotFound(BookingNotFoundException ex, HttpServletRequest request) {
		Map<String, Object> error = new HashMap<>();
		error.put(TIMESTAMP, LocalDateTime.now());
		error.put(STATUS, HttpStatus.NOT_FOUND.value());
		error.put(DESCRIPTION, ex.getMessage());
		error.put(INSTANCE, request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleFlightNotFound(FlightNotFoundException flightExcp, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(DESCRIPTION, flightExcp.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		errorDetails.put(INSTANCE, request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<Object> handleAirlineAdminNotFound(AirlineAdminNotFoundException ex) {
		Map<String, Object> error = new HashMap<>();
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.NOT_FOUND.value());
		error.put("error", "Not Found");
		error.put("message", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
			HttpServletRequest request) {
		Map<String, Object> error = new HashMap<>();
		error.put(TIMESTAMP, LocalDateTime.now());
		error.put(STATUS, HttpStatus.NOT_FOUND.value());
		error.put(DESCRIPTION, ex.getMessage());
		error.put(INSTANCE, request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailAlreadyExist.class)
	public ResponseEntity<Object> handleEmailAlreadyExist(EmailAlreadyExist ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(DESCRIPTION, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.CONFLICT.value());
		errorDetails.put(INSTANCE, request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(DESCRIPTION, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		errorDetails.put(INSTANCE, request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<Object> handleAirportNotFound(AirportNotFoundException ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(DESCRIPTION, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		errorDetails.put(INSTANCE, request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<Object> handlePaymentNotFound(PaymentNotFoundException ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(DESCRIPTION, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		errorDetails.put(INSTANCE, request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<Object> handleEmailNotFound(EmailNotFoundException ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(DESCRIPTION, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.NOT_FOUND.value());
		errorDetails.put(INSTANCE, request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put(TIMESTAMP, LocalDateTime.now());
		errorDetails.put(DESCRIPTION, ex.getMessage());
		errorDetails.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDetails.put(INSTANCE, request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
