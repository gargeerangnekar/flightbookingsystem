package com.capgemini.flightbookingsystem.dto;

import java.time.LocalDateTime;

public class BookingHistoryDto {

	private String name;
	private String flightNumber;
	private LocalDateTime arrivalTime;
	private String seatNumber;
	private LocalDateTime bookingTime;
	private String departureAirport;
	private String arrivalAirport;
	private Double amount;

	public BookingHistoryDto() {

	}

	public BookingHistoryDto(String name, String flightNumber, LocalDateTime arrivalTime, String seatNumber,
			LocalDateTime bookingTime, String departureAirport, String arrivalAirport) {
		super();
		this.name = name;
		this.flightNumber = flightNumber;
		this.arrivalTime = arrivalTime;
		this.seatNumber = seatNumber;
		this.bookingTime = bookingTime;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
	}
	
	public BookingHistoryDto(String name, String flightNumber, LocalDateTime arrivalTime, String seatNumber,
			LocalDateTime bookingTime, String departureAirport, String arrivalAirport,Double amount) {
		super();
		this.name = name;
		this.flightNumber = flightNumber;
		this.arrivalTime = arrivalTime;
		this.seatNumber = seatNumber;
		this.bookingTime = bookingTime;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.amount = amount;
	}
	

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

}
