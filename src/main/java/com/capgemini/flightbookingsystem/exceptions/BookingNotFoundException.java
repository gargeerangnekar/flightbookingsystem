package com.capgemini.flightbookingsystem.exceptions;

public class BookingNotFoundException extends RuntimeException {

	public BookingNotFoundException(String message) {
		super(message);
	}

}
