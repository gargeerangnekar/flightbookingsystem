package com.capgemini.flightbookingsystem.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

//entity
@Entity
@Table(name = "flight_table")
public class Flights {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_id")
	@NotNull
	protected Integer flightId;

	@Column(name = "flight_number")
	@NotNull
	protected String flightNumber;

	@NotNull
	@Column(name = "departure_time")
	protected LocalDateTime departureTime;
	
	@NotNull
	@Column(name = "arrival_time")
	protected LocalDateTime arrivalTime;

	@NotNull
	@Column(name = "status")
	protected String status;

	@NotNull
	@Column(name = "aircraft_model")
	protected String aircraftModel;

	@NotNull
	@Column(name = "capacity")
	protected Integer capacity;

	@Column(name = "airline_admin_id")
	@NotNull
	protected Integer airlineAdminId;

	// FK - Airports Entity
	@Column(name = "arrival_airport_id")
	@NotNull
	protected Integer arrivalAirportId;

	// FK - Airports Entity
	@Column(name = "departure_airport_id")
	@NotNull
	protected Integer departureAirportId; 
	
	@OneToMany(mappedBy = "flights", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<Booking> bookings = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "airportId")
	@JsonBackReference
	Airport airport;

	public Flights() {
		super();
	}

	public Flights(@NotNull Integer flightId, @NotNull String flightNumber, @NotNull LocalDateTime departureTime,
			@NotNull LocalDateTime arrivalTime, @NotNull String status, @NotNull String aircraftModel,
			@NotNull Integer capacity, @NotNull Integer airlineAdminId, @NotNull Integer arrivalAirportId,
			@NotNull Integer departureAirportId) {
		this.flightId = flightId;
		this.flightNumber = flightNumber;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.status = status;
		this.aircraftModel = aircraftModel;
		this.capacity = capacity;
		this.airlineAdminId = airlineAdminId;
		this.arrivalAirportId = arrivalAirportId;
		this.departureAirportId = departureAirportId;
	}

	public Flights(@NotNull String flightNumber, @NotNull LocalDateTime departureTime,
			@NotNull LocalDateTime arrivalTime, @NotNull String status, @NotNull String aircraftModel,
			@NotNull Integer capacity, @NotNull Integer airlineAdminId, @NotNull Integer arrivalAirportId,
			@NotNull Integer departureAirportId) {
		this.flightNumber = flightNumber;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.status = status;
		this.aircraftModel = aircraftModel;
		this.capacity = capacity;
		this.airlineAdminId = airlineAdminId;
		this.arrivalAirportId = arrivalAirportId;
		this.departureAirportId = departureAirportId;
	}

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAircraftModel() {
		return aircraftModel;
	}

	public void setAircraftModel(String aircraftModel) {
		this.aircraftModel = aircraftModel;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getAirlineAdminId() {
		return airlineAdminId;
	}

	public void setAirlineAdminId(Integer airlineAdminId) {
		this.airlineAdminId = airlineAdminId;
	}

	public Integer getArrivalAirportId() {
		return arrivalAirportId;
	}

	public void setArrivalAirportId(Integer arrivalAirportId) {
		this.arrivalAirportId = arrivalAirportId;
	}

	public Integer getDepartureAirportId() {
		return departureAirportId;
	}

	public void setDepartureAirportId(Integer departureAirportId) {
		this.departureAirportId = departureAirportId;
	}
	

	
	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	@Override
	public String toString() {
		return "Flights [flightId=" + flightId + ", flightNumber=" + flightNumber + ", departureTime=" + departureTime
				+ ", arrivalTime=" + arrivalTime + ", status=" + status + ", aircraftModel=" + aircraftModel
				+ ", capacity=" + capacity + ", airlineAdminId=" + airlineAdminId + ", arrivalAirportId="
				+ arrivalAirportId + ", departureAirportId=" + departureAirportId + ", bookings=" + bookings + "]";
	}

	

	
	
}
