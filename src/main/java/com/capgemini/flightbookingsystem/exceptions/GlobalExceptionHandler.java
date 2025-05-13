package com.capgemini.flightbookingsystem.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
