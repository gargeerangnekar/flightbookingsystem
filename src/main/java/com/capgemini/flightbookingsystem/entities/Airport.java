package com.capgemini.flightbookingsystem.entities;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//6
//Airport entity
@Entity
@Table(name = "airport_table")
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "airport id is mandatory")
	@Column(name = "airport_id")
	private Integer airportId;

	@NotBlank(message = "airport name is mandatory")
	@Column(name = "airport_name")
	private String airportName;

	@NotBlank(message = "airport city is mandatory")
	@Column(name = "city")
	private String city;

	@NotNull(message = "airport contact is mandatory")
	@Column(name = "contact")
	private Integer contact;

//	@OneToMany(mappedBy = "airport")
//	private List<Flights> flights;

	public Airport() {
		super();
	}

	public Airport(@NotNull Integer airportId, String airportName, @NotBlank String city, @NotNull Integer contact) {
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

	public Integer getContact() {
		return contact;
	}

	public void setContact(Integer contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Airport [airportId=" + airportId + ", airportName=" + airportName + ", city=" + city + ", contact="
				+ contact;
	}

}
