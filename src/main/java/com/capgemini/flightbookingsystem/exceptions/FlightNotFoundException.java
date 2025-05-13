package com.capgemini.flightbookingsystem.exceptions;


public class FlightNotFoundException extends RuntimeException{

	public FlightNotFoundException(String message) {
		super(message);
	}
}
