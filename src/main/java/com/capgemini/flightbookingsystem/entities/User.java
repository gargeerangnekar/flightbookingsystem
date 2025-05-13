package com.capgemini.flightbookingsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotBlank(message = "User name is mendentory.")
	private String name;

	@Email(message = "Enter valid email")
	private String email;

	@NotBlank(message = "Enter valid password")
	private String password;

	@NotBlank(message = "This field is compulsary.")
	private String phoneNumber;

	@NotNull(message = "Passport number is mandatory.")
	private Long passportNumber;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Long userId, String name, String email, String password, String phoneNumber, Long passportNumber) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.passportNumber = passportNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(Long passportNumber) {
		this.passportNumber = passportNumber;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", passportNumber=" + passportNumber + "]";
	}

}
