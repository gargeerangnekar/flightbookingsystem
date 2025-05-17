package com.capgemini.flightbookingsystem.dto;

public class AirportFetchingDto {

	String departureCity;
	
	String arrivalCity;

	public AirportFetchingDto() {
		super();
	}

	public AirportFetchingDto(String departureCity, String arrivalCity) {
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	
	
}
