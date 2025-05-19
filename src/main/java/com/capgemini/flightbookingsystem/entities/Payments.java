package com.capgemini.flightbookingsystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

//11
@Entity
@Table(name = "payment_table")
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

	@Override
	public String toString() {
		return "Payments [paymentId=" + paymentId + ", amount=" + amount + ", paymentDatetime=" + paymentDatetime + "]";
	}
}
