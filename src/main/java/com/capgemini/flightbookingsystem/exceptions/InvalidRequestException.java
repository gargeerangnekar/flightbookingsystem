package com.capgemini.flightbookingsystem.exceptions;

public class InvalidRequestException extends RuntimeException{

	public InvalidRequestException(String message) {
        super(message);
    }
}
