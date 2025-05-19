package com.capgemini.flightbookingsystem.dto;

public class CityWithAirportIdDto {
	private Integer airportId;
    private String city;

    public CityWithAirportIdDto() {
    }

    public CityWithAirportIdDto(Integer airportId, String city) {
        this.airportId = airportId;
        this.city = city;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
