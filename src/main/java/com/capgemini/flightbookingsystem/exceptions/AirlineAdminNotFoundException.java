package com.capgemini.flightbookingsystem.exceptions;

public class AirlineAdminNotFoundException extends RuntimeException {

    public AirlineAdminNotFoundException(String message) {
        super(message);
    }
