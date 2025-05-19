package com.capgemini.flightbookingsystem.dto;

import java.time.LocalDateTime;

public class BookingCardDTO {
    private Integer bookingId;
    private String passengerName;
    private String passportNumber;
    private String flightNumber;
    private String aircraftModel;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private LocalDateTime bookingTime;
    private String seatNumber;
    private String seatClass;
    private Double amount;
    
	public BookingCardDTO() {
	}


	public BookingCardDTO(Integer bookingId, String passengerName, String passportNumber, String flightNumber,
			String aircraftModel, String departureAirport, String arrivalAirport, LocalDateTime departureTime,
			LocalDateTime arrivalTime, LocalDateTime bookingTime, String seatNumber, String seatClass, Double amount) {
		super();
		this.bookingId = bookingId;
		this.passengerName = passengerName;
		this.passportNumber = passportNumber;
		this.flightNumber = flightNumber;
		this.aircraftModel = aircraftModel;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.bookingTime = bookingTime;
		this.seatNumber = seatNumber;
		this.seatClass = seatClass;
		this.amount = amount;
	}


	public Integer getBookingId() {
		return bookingId;
	}


	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}


	public String getPassengerName() {
		return passengerName;
	}


	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}


	public String getPassportNumber() {
		return passportNumber;
	}


	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}


	public String getFlightNumber() {
		return flightNumber;
	}


	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}


	public String getAircraftModel() {
		return aircraftModel;
	}


	public void setAircraftModel(String aircraftModel) {
		this.aircraftModel = aircraftModel;
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


	public LocalDateTime getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}


	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public LocalDateTime getBookingTime() {
		return bookingTime;
	}


	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}


	public String getSeatNumber() {
		return seatNumber;
	}


	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}


	public String getSeatClass() {
		return seatClass;
	}


	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}
    
}