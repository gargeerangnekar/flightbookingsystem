package com.capgemini.flightbookingsystem.entities;

import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Flights {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	protected Long flightId;

	@NotNull
	protected Long flightNumber;

	protected LocalTime departureTime;

	protected LocalTime arrivalTime;

	protected String status;

	protected String aircraftModel;

	protected String capacity;

	@NotNull
	protected Long airlineAdminId;

	// FK - Airports Entity
	@NotNull
	protected Long arrivalAirportId;

	// FK - Airports Entity
	@NotNull
	protected Long departureAirportId;

	// Default Contructor
	public Flights() {
		super();
	}

	// Constructor with Primary Key
	public Flights(@NotNull Long flightId, @NotNull Long flightNumber, LocalTime departureTime, LocalTime arrivalTime,
			String status, String aircraftModel, String capacity, @NotNull Long airlineAdminId,
			@NotNull Long arrivalAirportId, @NotNull Long departureAirportId) {
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

	// Constructor without Primary Key
	public Flights(@NotNull Long flightNumber, LocalTime departureTime, LocalTime arrivalTime, String status,
			String aircraftModel, String capacity, @NotNull Long airlineAdminId, @NotNull Long arrivalAirportId,
			@NotNull Long departureAirportId) {
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

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Long getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(Long flightNumber) {
		this.flightNumber = flightNumber;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
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

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public Long getAirlineAdminId() {
		return airlineAdminId;
	}

	public void setAirlineAdminId(Long airlineAdminId) {
		this.airlineAdminId = airlineAdminId;
	}

	public Long getArrivalAirportId() {
		return arrivalAirportId;
	}

	public void setArrivalAirportId(Long arrivalAirportId) {
		this.arrivalAirportId = arrivalAirportId;
	}

	public Long getDepartureAirportId() {
		return departureAirportId;
	}

	public void setDepartureAirportId(Long departureAirportId) {
		this.departureAirportId = departureAirportId;
	}

	@Override
	public String toString() {
		return "Flights [flightId=" + flightId + ", flightNumber=" + flightNumber + ", departureTime=" + departureTime
				+ ", arrivalTime=" + arrivalTime + ", status=" + status + ", aircraftModel=" + aircraftModel
				+ ", capacity=" + capacity + ", airlineAdminId=" + airlineAdminId + ", arrivalAirportId="
				+ arrivalAirportId + ", departureAirportId=" + departureAirportId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(aircraftModel, airlineAdminId, arrivalAirportId, arrivalTime, capacity, departureAirportId,
				departureTime, flightId, flightNumber, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flights other = (Flights) obj;
		return Objects.equals(aircraftModel, other.aircraftModel)
				&& Objects.equals(airlineAdminId, other.airlineAdminId)
				&& Objects.equals(arrivalAirportId, other.arrivalAirportId)
				&& Objects.equals(arrivalTime, other.arrivalTime) && Objects.equals(capacity, other.capacity)
				&& Objects.equals(departureAirportId, other.departureAirportId)
				&& Objects.equals(departureTime, other.departureTime) && Objects.equals(flightId, other.flightId)
				&& Objects.equals(flightNumber, other.flightNumber) && Objects.equals(status, other.status);
	}

	
}
