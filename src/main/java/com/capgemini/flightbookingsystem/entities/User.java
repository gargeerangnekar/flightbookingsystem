package com.capgemini.flightbookingsystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@NotBlank(message = "User name is mendentory.")
	@Column(name = "name")
	private String name;

	@Email(message = "Enter valid email")
	@Column(name = "email",unique = true)
	@NotBlank(message = "Email is mandatory")
	private String email;

	@NotBlank(message = "Enter valid password")
	@Column(name = "password")
	private String password;

	@NotBlank(message = "This field is compulsary.")
	@Column(name="phone_number")
	private String phoneNumber;

	@NotBlank(message = "Passport number is mandatory.")
	@Column(name = "passport_number")
	private String passportNumber;

	public User() {
	}

	public User(Integer userId, String name, String email, String password, String phoneNumber, String passportNumber) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.passportNumber = passportNumber;

	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", passportNumber=" + passportNumber + "]";
	}

}
