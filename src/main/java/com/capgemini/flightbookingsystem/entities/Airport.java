package com.capgemini.flightbookingsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "airport_table")
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "airport_id")
	private Integer airportId;

	@NotBlank(message = "airport name is mandatory")
	@Size(min = 2, max = 100, message = "Airport name must be between 2 and 100 characters")
	@Column(name = "airport_name")
	private String airportName;

	@NotBlank(message = "airport city is mandatory")
	@Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters")
	@Column(name = "city")
	private String city;

	@NotBlank(message = "Airport contact is mandatory")
	@Pattern(regexp = "\\d{10}", message = "Contact number must be exactly 10 digits")
	@Column(name = "contact")
	private String contact;
	public Airport() {
		super();
	}


	public Airport(Integer airportId,
			@NotBlank(message = "airport name is mandatory") @Size(min = 2, max = 100, message = "Airport name must be between 2 and 100 characters") String airportName,
			@NotBlank(message = "airport city is mandatory") @Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters") String city,
			@NotBlank(message = "Airport contact is mandatory") @Pattern(regexp = "\\d{10}", message = "Contact number must be exactly 10 digits") String contact) {
		super();
		this.airportId = airportId;
		this.airportName = airportName;
		this.city = city;
		this.contact = contact;
	}



	public Integer getAirportId() {
		return airportId;
	}

	public void setAirportId(Integer airportId) {
		this.airportId = airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Airport [airportId=" + airportId + ", airportName=" + airportName + ", city=" + city + ", contact="
				+ contact;
	}

}
