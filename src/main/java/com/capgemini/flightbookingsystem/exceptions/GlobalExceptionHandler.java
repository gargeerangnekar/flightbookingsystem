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
	
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleFlightNotFound(FlightNotFoundException flightExcp, HttpServletRequest request){
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("timestamp", LocalDateTime.now());
		errorDetails.put("message", flightExcp.getMessage());
		errorDetails.put("status", HttpStatus.NOT_FOUND.value());
		errorDetails.put("instance", request.getRequestURI());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
}
