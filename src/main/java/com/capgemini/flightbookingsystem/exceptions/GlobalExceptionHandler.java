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

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Object> handleBookingNotFound(BookingNotFoundException ex) {
		Map<String, Object> error = new HashMap<String, Object>();
		error.put("Timestamp:", LocalDateTime.now());
		error.put("Status:", HttpStatus.NOT_FOUND.value());
		error.put("Description:", ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleFlightNotFound(FlightNotFoundException flightExcp, HttpServletRequest request){
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", flightExcp.getMessage());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		errorDetails.put("instance", request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(AirlineAdminNotFoundException.class)
	public ResponseEntity<Object> handleAirlineAdminNotFound(AirlineAdminNotFoundException ex) {
	    Map<String, Object> error = new HashMap<>();
	    error.put("Timestamp:", LocalDateTime.now());
	    error.put("Status:", HttpStatus.NOT_FOUND.value());
	    error.put("Description:", ex.getMessage());
	    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(EmailAlreadyExist.class)
	public ResponseEntity<Object> handleEmailAlreadyExist(EmailAlreadyExist ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("status", HttpStatus.CONFLICT.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", ex.getMessage());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

}
