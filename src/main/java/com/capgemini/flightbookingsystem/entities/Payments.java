package com.capgemini.flightbookingsystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
public class Payments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentId;

	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must be positive")
	private Double amount;

	@NotNull(message = "Payment date is required")
	@PastOrPresent(message = "Payment date cannot be in the future")
	private LocalDateTime paymentDatetime;

	@NotNull(message = "Booking ID is required")
	@Positive(message = "Booking ID must be a positive number")
	private Integer bookingId;

	@NotNull(message = "User ID is required")
	@Positive(message = "User ID must be a positive number")
	private Integer userId;

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getPaymentDatetime() {
		return paymentDatetime;
	}

	public void setPaymentDatetime(LocalDateTime paymentDatetime) {
		this.paymentDatetime = paymentDatetime;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Payments [paymentId=" + paymentId + ", amount=" + amount + ", paymentDatetime=" + paymentDatetime
				+ ", bookingId=" + bookingId + ", userId=" + userId + "]";
	}

}
