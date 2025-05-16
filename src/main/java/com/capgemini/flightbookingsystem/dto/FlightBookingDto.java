package com.capgemini.flightbookingsystem.dto;

import java.time.LocalDateTime;

public class FlightBookingDto {

	private Integer departureAirportId;
	private Integer arrivalAirportId;
	private String airportName;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private Double amount;

	public FlightBookingDto() {

	}

	public FlightBookingDto(Integer departureAirportId, Integer arrivalAirportId, String airportName,
			LocalDateTime departureTime, LocalDateTime arrivalTime, Double amount) {
		super();
		this.departureAirportId = departureAirportId;
		this.arrivalAirportId = arrivalAirportId;
		this.airportName = airportName;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.amount = amount;
	}

	public Integer getDepartureAirportId() {
		return departureAirportId;
	}

	public void setDepartureAirportId(Integer departureAirportId) {
		this.departureAirportId = departureAirportId;
	}

	public Integer getArrivalAirportId() {
		return arrivalAirportId;
	}

	public void setArrivalAirportId(Integer arrivalAirportId) {
		this.arrivalAirportId = arrivalAirportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "FlightBookingDto [departureAirportId=" + departureAirportId + ", arrivalAirportId=" + arrivalAirportId
				+ ", airportName=" + airportName + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", amount=" + amount + "]";
	}

}
