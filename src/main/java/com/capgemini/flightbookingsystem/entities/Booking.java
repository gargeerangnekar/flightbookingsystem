package com.capgemini.flightbookingsystem.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//3
@Entity
@Table(name = "booking_table")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Integer bookingId;

	@Column(name = "seat_number")
	@NotBlank(message = "Seat number cannot be empty")
	private String seatNumber;

	@Column(name = "seat_class")
	@NotBlank(message = "Seat Class cannot be empty")
	private String seatClass;

	@Column(name = "booking_time")
	@NotBlank(message = "Booking time cannot be empty")
	private LocalDateTime bookingTime;

	@Column(name = "status")
	@NotBlank(message = "Booking Status cannot be empty")
	private String status;

	@Column(name = "amount")
	@NotNull(message = "Booking amount cannot be empty")
	private Double amount;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId")
	private User users;

//	@Column(name = "user_id")
//	@NotNull(message = "User Id cannot be empty")
//	private Long userId;

//	@Column(name = "flight_id")
//	@NotNull(message = "Flight Id cannot be empty")
//	private Long flightId;

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "flightId")
	private Flights flights;

	public Booking() {

	}

	public Booking(Integer bookingId, @NotBlank(message = "Seat number cannot be empty") String seatNumber,
			@NotBlank(message = "Seat Class cannot be empty") String seatClass,
			@NotBlank(message = "Booking time cannot be empty") LocalDateTime bookingTime,
			@NotBlank(message = "Booking Status cannot be empty") String status,
			@NotNull(message = "Booking amount cannot be empty") Double amount, User users, Flights flights) {
		super();
		this.bookingId = bookingId;
		this.seatNumber = seatNumber;
		this.seatClass = seatClass;
		this.bookingTime = bookingTime;
		this.status = status;
		this.amount = amount;
		this.users = users;
		this.flights = flights;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public LocalDateTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Flights getFlights() {
		return flights;
	}

	public void setFlights(Flights flights) {
	    this.flights = flights;
	}


	@Override
	public String toString() {
		return "Bookings [bookingId=" + bookingId + ", seatNumber=" + seatNumber + ", seatClass=" + seatClass
				+ ", bookingTime=" + bookingTime + ", status=" + status + "]";
	}

}