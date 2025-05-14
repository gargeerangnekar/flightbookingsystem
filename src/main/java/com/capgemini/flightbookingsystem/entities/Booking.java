package com.capgemini.flightbookingsystem.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	private String seatNumber;
	private String seatClass;
	private LocalDate bookingDate;
	private LocalDateTime bookingTime;
	private String status;
	private Long amount;
	private Long userId;
	private Long flightId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JsonBackReference
	@JoinColumn(name = "flightId")
	private Flights flights;

	public Booking() {

	}

	public Booking(Long bookingId, String seatNumber, String seatClass, LocalDate bookingDate,
			LocalDateTime bookingTime, String status, Long amount, Long userId, Long flightId) {
		super();
		this.bookingId = bookingId;
		this.seatNumber = seatNumber;
		this.seatClass = seatClass;
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.status = status;
		this.amount = amount;
		this.userId = userId;
		this.flightId = flightId;
	}

	public Booking(String seatNumber, String seatClass, LocalDate bookingDate, LocalDateTime bookingTime,
			String status, Long amount, Long userId, Long flightId) {
		super();
		this.seatNumber = seatNumber;
		this.seatClass = seatClass;
		this.bookingDate = bookingDate;
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

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
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
	
	

	public Flights getFlights() {
		return flights;
	}

	public void setFlights(Flights flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "Bookings [bookingId=" + bookingId + ", seatNumber=" + seatNumber + ", seatClass=" + seatClass
				+ ", bookingDate=" + bookingDate + ", bookingTime=" + bookingTime + ", status=" + status + ", userId="
				+ userId + ", flightId=" + flightId + "]";
	}

}
