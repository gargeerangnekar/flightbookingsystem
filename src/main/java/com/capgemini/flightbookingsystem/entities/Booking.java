package com.capgemini.flightbookingsystem.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "booking_table")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long bookingId;

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
	private Long amount;

	@Column(name = "user_id")
	@NotNull(message = "User Id cannot be empty")
	private Long userId;

	@Column(name = "flight_id")
	@NotNull(message = "Flight Id cannot be empty")
	private Long flightId;

	public Booking() {

	}

	public Booking(Long bookingId, String seatNumber, String seatClass, LocalDateTime bookingTime, String status,
			Long amount, Long userId, Long flightId) {
		super();
		this.bookingId = bookingId;
		this.seatNumber = seatNumber;
		this.seatClass = seatClass;
		this.bookingTime = bookingTime;
		this.status = status;
		this.amount = amount;
		this.userId = userId;
		this.flightId = flightId;
	}

	public Booking(String seatNumber, String seatClass, LocalDateTime bookingTime, String status, Long amount,
			Long userId, Long flightId) {
		super();
		this.seatNumber = seatNumber;
		this.seatClass = seatClass;
		this.bookingTime = bookingTime;
		this.status = status;
		this.amount = amount;
		this.userId = userId;
		this.flightId = flightId;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
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

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	@Override
	public String toString() {
		return "Bookings [bookingId=" + bookingId + ", seatNumber=" + seatNumber + ", seatClass=" + seatClass
				+ ", bookingTime=" + bookingTime + ", status=" + status + ", userId=" + userId + ", flightId="
				+ flightId + "]";
	}

}
