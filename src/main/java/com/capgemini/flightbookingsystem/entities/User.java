package com.capgemini.flightbookingsystem.entities;


//12
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@NotBlank(message = "User name is mendentory.")
	@Column(name = "name")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;

	@Email(message = "Enter valid email")
	@Column(name = "email",unique = true)
	@NotBlank(message = "Email is mandatory")
	private String email;

	@NotBlank(message = "Enter valid password")
	@Column(name = "password")
	//@Size(min = 8, max = 15, message = "Password must be at least 8 characters long")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
	message = "Password must contain at least one uppercase, one lowercase, one digit, and one special character") 
	private String password;

	@NotBlank(message = "This field is compulsary.")
	@Column(name="phone_number")
	@Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be 10 to 15 digits") 
	private String phoneNumber;

	@NotBlank(message = "Passport number is mandatory.")
	@Column(name = "passport_number")
	@Pattern(regexp = "^[A-Z0-9]{6,9}$", message = "Passport number must be 6 to 9 alphanumeric characters") 
	private String passportNumber;

	
	@OneToMany(mappedBy = "users",cascade = CascadeType.PERSIST)
	@JsonManagedReference
	List<Booking> bookings = new ArrayList<>();
	
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
