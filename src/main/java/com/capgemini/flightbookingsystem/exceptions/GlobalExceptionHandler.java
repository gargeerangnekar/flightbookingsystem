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
public class GlobalExceptionHandler{

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Object> handleBookingNotFound(BookingNotFoundException ex, HttpServletRequest request) {
		Map<String, Object> error = new HashMap<String, Object>();
		error.put("Timestamp:", LocalDateTime.now());
		error.put("Status:", HttpStatus.NOT_FOUND.value());
		error.put("Description:", ex.getMessage());
		error.put("instance", request.getRequestURI());
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleFlightNotFound(FlightNotFoundException flightExcp, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", flightExcp.getMessage());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		errorDetails.put("instance", request.getRequestURI());
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
		error.put("Timestamp:", LocalDateTime.now());
		error.put("Status:", HttpStatus.NOT_FOUND.value());
		error.put("Description:", ex.getMessage());
		error.put("instance", request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailAlreadyExist.class)
	public ResponseEntity<Object> handleEmailAlreadyExist(EmailAlreadyExist ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("status", HttpStatus.CONFLICT.value());
		errorDetails.put("instance", request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		errorDetails.put("instance", request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<Object> handleAirportNotFound(AirportNotFoundException ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		errorDetails.put("instance", request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<Object> PaymentNotFound(PaymentNotFoundException ex, HttpServletRequest request) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		errorDetails.put("instance", request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
