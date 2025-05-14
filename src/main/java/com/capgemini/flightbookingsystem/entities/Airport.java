package com.capgemini.flightbookingsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
	@Table(name = "airport")
	public class Airport {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "airport_id")
	    private Long airportId;
	    @Column(name = "airport_name", length = 255)
	    
	    private String airportName;

	    @Column(name = "city", length = 255)
	    private String city;

	    @Column(name = "contact")
	    private Integer contact;

		public Airport() {
			super();
		}

		public Airport(Long airportId, String airportName, String city, Integer contact) {
			super();
			this.airportId = airportId;
			this.airportName = airportName;
			this.city = city;
			this.contact = contact;
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

	    
	}

