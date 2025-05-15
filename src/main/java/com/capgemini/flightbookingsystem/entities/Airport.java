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

@Entity
@Table(name = "airport")
public class Airport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "airport id is mandatory")
	@Column(name = "airport_id")
	private Long airportId;

	@NotBlank(message = "airport name is mandatory")
	@Column(name = "airport_name")
	private String airportName;

	@NotBlank(message = "airport city is mandatory")
	@Column(name = "city")
	private String city;

	@NotNull(message = "airport contact is mandatory")
	@Column(name = "contact")
	private Integer contact;

	@OneToMany(mappedBy = "airport")
	private List<Flights> flights;

	public Airport() {
		super();
	}

	public Airport(@NotNull Long airportId, String airportName, @NotBlank String city, @NotNull Integer contact,
			@NotNull List<Flights> flights) {
		super();
		this.airportId = airportId;
		this.airportName = airportName;
		this.city = city;
		this.contact = contact;
		this.flights = flights;
	}

	public Long getAirportId() {
		return airportId;
	}

	public void setAirportId(Long airportId) {
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

	public List<Flights> getFlights() {
		return flights;
	}

	public void setFlights(List<Flights> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Airport [airportId=" + airportId + ", airportName=" + airportName + ", city=" + city + ", contact="
				+ contact + ", flights=" + flights + "]";
	}

}
