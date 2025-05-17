package com.capgemini.flightbookingsystem.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "flight_table")
public class Flights {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_id")
	protected Integer flightId;

	@Column(name = "flight_number")
	@NotNull(message = "Flight number is required")
	@Size(min = 2, max = 10, message = "Flight number must be between 2 and 10 characters")
	protected String flightNumber;

	@NotNull(message = "Departure time is required")
	@FutureOrPresent(message = "Departure time must be in the present or future")
	@Column(name = "departure_time")
	protected LocalDateTime departureTime;
	
	@NotNull(message = "Arrival time is required")
	@Future(message = "Arrival time must be in the future")
	@Column(name = "arrival_time")
	protected LocalDateTime arrivalTime;

	
	@NotNull(message = "Amount is required")
	@Column(name = "amount")
	protected Double amount;


	@NotNull(message = "Aircraft model is required")
	@Column(name = "aircraft_model")
	protected String aircraftModel;

	@NotNull(message = "Capacity is required")
	@Min(value = 1, message = "Capacity must be at least 1")
	@Max(value = 1000, message = "Capacity cannot exceed 1000")
	@Column(name = "capacity")
	protected Integer capacity;

	// FK - Airports Entity
	@NotNull(message = "Arrival airport ID is required")
	@Positive(message = "Arrival airport ID must be a positive integer")
	@Column(name = "arrival_airport_id")
	protected Integer arrivalAirportId;

	// FK - Airports Entity
	@Column(name = "departure_airport_id")
	@NotNull(message = "Departure airport ID is required")
	@Positive(message = "Departure airport ID must be a positive integer")
	protected Integer departureAirportId; 
	
	
	// 1. Flight to Booking - One flight can have multiple bookings
	@OneToMany(mappedBy = "flights", cascade = CascadeType.ALL)
	@JsonManagedReference("flight-booking")
	List<Booking> bookings = new ArrayList<>();
	
	
	// 2. Airline Admin to Flight - One airline admin can handle multiple flights
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "airline_admin_id")
	@JsonBackReference("airline")
	AirLineAdmin airlineAdmin;

	public Flights() {
		super();
	}

	// Constructor with flight ID (PK)
	public Flights(@NotNull Integer flightId,
			@NotNull(message = "Flight number is required") @Size(min = 2, max = 10, message = "Flight number must be between 2 and 10 characters") String flightNumber,
			@NotNull(message = "Departure time is required") @FutureOrPresent(message = "Departure time must be in the present or future") LocalDateTime departureTime,
			@NotNull(message = "Arrival time is required") @Future(message = "Arrival time must be in the future") LocalDateTime arrivalTime,
			Double amount, @NotNull(message = "Aircraft model is required") String aircraftModel,
			@NotNull(message = "Capacity is required") @Min(value = 1, message = "Capacity must be at least 1") @Max(value = 1000, message = "Capacity cannot exceed 1000") Integer capacity,
			@NotNull(message = "Arrival airport ID is required") @Positive(message = "Arrival airport ID must be a positive integer") @NotNull Integer arrivalAirportId,
			@NotNull(message = "Departure airport ID is required") @Positive(message = "Departure airport ID must be a positive integer") Integer departureAirportId,
			List<Booking> bookings, AirLineAdmin airlineAdmin) {
		this.flightId = flightId;
		this.flightNumber = flightNumber;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.amount = amount;
		this.aircraftModel = aircraftModel;
		this.capacity = capacity;
		this.arrivalAirportId = arrivalAirportId;
		this.departureAirportId = departureAirportId;
		this.bookings = bookings;
		this.airlineAdmin = airlineAdmin;
	}
	
	// Constructor with flight ID (PK)
		public Flights(@NotNull Integer flightId,
				@NotNull(message = "Flight number is required") @Size(min = 2, max = 10, message = "Flight number must be between 2 and 10 characters") String flightNumber,
				@NotNull(message = "Departure time is required") @FutureOrPresent(message = "Departure time must be in the present or future") LocalDateTime departureTime,
				@NotNull(message = "Arrival time is required") @Future(message = "Arrival time must be in the future") LocalDateTime arrivalTime,
				Double amount, @NotNull(message = "Aircraft model is required") String aircraftModel,
				@NotNull(message = "Capacity is required") @Min(value = 1, message = "Capacity must be at least 1") @Max(value = 1000, message = "Capacity cannot exceed 1000") Integer capacity,
				@NotNull(message = "Arrival airport ID is required") @Positive(message = "Arrival airport ID must be a positive integer") @NotNull Integer arrivalAirportId,
				@NotNull(message = "Departure airport ID is required") @Positive(message = "Departure airport ID must be a positive integer") Integer departureAirportId,
				AirLineAdmin airlineAdmin) {
			this.flightId = flightId;
			this.flightNumber = flightNumber;
			this.departureTime = departureTime;
			this.arrivalTime = arrivalTime;
			this.amount = amount;
			this.aircraftModel = aircraftModel;
			this.capacity = capacity;
			this.arrivalAirportId = arrivalAirportId;
			this.departureAirportId = departureAirportId;
			this.airlineAdmin = airlineAdmin;
		}
	
	// Constructor with flight ID (PK)
	public Flights(@NotNull Integer flightId,
			@NotNull(message = "Flight number is required") @Size(min = 2, max = 10, message = "Flight number must be between 2 and 10 characters") String flightNumber,
			@NotNull(message = "Departure time is required") @FutureOrPresent(message = "Departure time must be in the present or future") LocalDateTime departureTime,
			@NotNull(message = "Arrival time is required") @Future(message = "Arrival time must be in the future") LocalDateTime arrivalTime,
			@NotNull(message = "Aircraft model is required") String aircraftModel, Double amount,
			@NotNull(message = "Capacity is required") @Min(value = 1, message = "Capacity must be at least 1") @Max(value = 1000, message = "Capacity cannot exceed 1000") Integer capacity,
			@NotNull(message = "Arrival airport ID is required") @Positive(message = "Arrival airport ID must be a positive integer") @NotNull Integer arrivalAirportId,
			@NotNull(message = "Departure airport ID is required") @Positive(message = "Departure airport ID must be a positive integer") Integer departureAirportId) {
		this.flightId = flightId;
		this.flightNumber = flightNumber;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.aircraftModel = aircraftModel;
		this.capacity = capacity;
		this.amount = amount;
		this.arrivalAirportId = arrivalAirportId;
		this.departureAirportId = departureAirportId;
	}

		public Flights(@NotNull Integer flightId,
				@NotNull(message = "Flight number is required") @Size(min = 2, max = 10, message = "Flight number must be between 2 and 10 characters") String flightNumber,
				@NotNull(message = "Departure time is required") @FutureOrPresent(message = "Departure time must be in the present or future") LocalDateTime departureTime,
				@NotNull(message = "Arrival time is required") @Future(message = "Arrival time must be in the future") LocalDateTime arrivalTime,
				Double amount,
				@NotNull(message = "Aircraft model is required") String aircraftModel,
				@NotNull(message = "Capacity is required") @Min(value = 1, message = "Capacity must be at least 1") @Max(value = 1000, message = "Capacity cannot exceed 1000") Integer capacity,
				@NotNull(message = "Arrival airport ID is required") @Positive(message = "Arrival airport ID must be a positive integer") @NotNull Integer arrivalAirportId,
				@NotNull(message = "Departure airport ID is required") @Positive(message = "Departure airport ID must be a positive integer") Integer departureAirportId) {
			this.flightId = flightId;
			this.flightNumber = flightNumber;
			this.departureTime = departureTime;
			this.arrivalTime = arrivalTime;
			this.amount = amount;
			this.aircraftModel = aircraftModel;
			this.capacity = capacity;
			this.arrivalAirportId = arrivalAirportId;
			this.departureAirportId = departureAirportId;
		}
	
	// Constructor without flight ID (PK)
	public Flights(
			@NotNull(message = "Flight number is required") @Size(min = 2, max = 10, message = "Flight number must be between 2 and 10 characters") String flightNumber,
			@NotNull(message = "Departure time is required") @FutureOrPresent(message = "Departure time must be in the present or future") LocalDateTime departureTime,
			@NotNull(message = "Arrival time is required") @Future(message = "Arrival time must be in the future") LocalDateTime arrivalTime,
			Double amount,
			@NotNull(message = "Aircraft model is required") String aircraftModel,
			@NotNull(message = "Capacity is required") @Min(value = 1, message = "Capacity must be at least 1") @Max(value = 1000, message = "Capacity cannot exceed 1000") Integer capacity,
			@NotNull(message = "Arrival airport ID is required") @Positive(message = "Arrival airport ID must be a positive integer") @NotNull Integer arrivalAirportId,
			@NotNull(message = "Departure airport ID is required") @Positive(message = "Departure airport ID must be a positive integer") Integer departureAirportId,
			List<Booking> bookings, AirLineAdmin airlineAdmin) {
		this.flightNumber = flightNumber;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.amount = amount;
		this.aircraftModel = aircraftModel;
		this.capacity = capacity;
		this.arrivalAirportId = arrivalAirportId;
		this.departureAirportId = departureAirportId;
		this.bookings = bookings;
		this.airlineAdmin = airlineAdmin;
	}
	
	// Constructor without mappings
	public Flights(
			@NotNull(message = "Flight number is required") @Size(min = 2, max = 10, message = "Flight number must be between 2 and 10 characters") String flightNumber,
			@NotNull(message = "Departure time is required") @FutureOrPresent(message = "Departure time must be in the present or future") LocalDateTime departureTime,
			@NotNull(message = "Arrival time is required") @Future(message = "Arrival time must be in the future") LocalDateTime arrivalTime,
			Double amount,
			@NotNull(message = "Aircraft model is required") String aircraftModel,
			@NotNull(message = "Capacity is required") @Min(value = 1, message = "Capacity must be at least 1") @Max(value = 1000, message = "Capacity cannot exceed 1000") Integer capacity,
			@NotNull(message = "Arrival airport ID is required") @Positive(message = "Arrival airport ID must be a positive integer") @NotNull Integer arrivalAirportId,
			@NotNull(message = "Departure airport ID is required") @Positive(message = "Departure airport ID must be a positive integer") Integer departureAirportId) {
		this.flightNumber = flightNumber;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.amount = amount;
		this.aircraftModel = aircraftModel;
		this.capacity = capacity;
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
	

	public AirLineAdmin getAirlineAdmin() {
		return airlineAdmin;
	}

	public void setAirlineAdmin(AirLineAdmin airlineAdmin) {
		this.airlineAdmin = airlineAdmin;
	}

	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Flights [flightId=" + flightId + ", flightNumber=" + flightNumber + ", departureTime=" + departureTime
				+ ", arrivalTime=" + arrivalTime + ", aircraftModel=" + aircraftModel + ", amount=" + amount
				+ ", capacity=" + capacity + ", arrivalAirportId=" + arrivalAirportId + ", departureAirportId="
				+ departureAirportId + ", bookings=" + bookings + ", airlineAdmin=" + airlineAdmin + "]";
	}

	

	
	
}
