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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "booking_table")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Integer bookingId;

	@Column(name = "seat_number")
	@NotNull(message = "Seat number cannot be empty")
	private String seatNumber;

	@Column(name = "seat_class")
	@NotNull(message = "Seat Class cannot be empty")
	private String seatClass;

	@Column(name = "booking_time")
	@NotNull(message = "Booking time cannot be empty")
	private LocalDateTime bookingTime;

	@Column(name = "amount")
	@NotNull(message = "Booking amount cannot be empty")
	@Positive(message = "Amount must be positive")
	private Double amount;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User users;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payments payment;

	@JsonBackReference("flight-booking")
	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flights flights;
	
	public Booking() {
		
	}

	public Booking(Integer bookingId, @NotNull(message = "Seat number cannot be empty") String seatNumber,
			@NotNull(message = "Seat Class cannot be empty") String seatClass,
			@NotNull(message = "Booking time cannot be empty") LocalDateTime bookingTime,
			@NotNull(message = "Booking amount cannot be empty") @Positive(message = "Amount must be positive") Double amount,
			User users, Payments payment, Flights flights) {
		super();
		this.bookingId = bookingId;
		this.seatNumber = seatNumber;
		this.seatClass = seatClass;
		this.bookingTime = bookingTime;
		this.amount = amount;
		this.users = users;
		this.payment = payment;
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

	public Payments getPayment() {
		return payment;
	}

	public void setPayment(Payments payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", seatNumber=" + seatNumber + ", seatClass=" + seatClass
				+ ", bookingTime=" + bookingTime + ", amount=" + amount + ", users=" + users + ", payment=" + payment
				+ ", flights=" + flights + "]";
	}

}