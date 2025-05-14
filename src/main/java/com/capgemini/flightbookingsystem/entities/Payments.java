package com.capgemini.flightbookingsystem.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Payments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Integer paymentId;

	@Column(nullable = false)
	private Double amount;

	@Column(name = "payment_datetime", nullable = false)
	private LocalDateTime paymentDatetime;

	@Column(name = "booking_id", nullable = false)
	private Integer bookingId;

	@Column(name = "user_id", nullable = false)
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
