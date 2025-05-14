package com.capgemini.flightbookingsystem.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailAlreadyExist extends RuntimeException {
	public EmailAlreadyExist(String message) {
		super(message);
	}
}
