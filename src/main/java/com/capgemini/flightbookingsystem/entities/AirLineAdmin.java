package com.capgemini.flightbookingsystem.entities;

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
@Table(name = "airline_admin")
public class AirLineAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_admin_id")
    private Integer airlineAdminId;

    @NotBlank(message = "Airline Admin name is required")
    @Size(min = 2, max = 100, message = "Airline Admin name must be between 2 and 100 characters")
    @Column(name = "airline_admin_name")
    private String airlineAdminName;

    @NotBlank(message = "Password is required")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
        message = "Password must include upper and lower case letters, a number, and a special character"
    )
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^\\d{10,15}$", message = "Contact number must be 10 to 15 digits")
    @Column(name = "contact_number")
    private String contactNumber;

    @NotBlank(message = "Airline email is required")
    @Email(message = "Enter a valid airline email")
    @Column(name = "airline_email")
    private String airlineEmail;

    @OneToMany(mappedBy = "airlineAdmin", cascade = CascadeType.ALL)

	@JsonManagedReference("airline")
	List<Flights> flights = new ArrayList<>();

    public AirLineAdmin() {
        super();
    }

    public AirLineAdmin(Integer airlineAdminId, String airlineAdminName, String password, String contactNumber, String airlineEmail) {
        super();
        this.airlineAdminId = airlineAdminId;
        this.airlineAdminName = airlineAdminName;
        this.password = password;
        this.contactNumber = contactNumber;
        this.airlineEmail = airlineEmail;
    }

    public Integer getAirlineAdminId() {
        return airlineAdminId;
    }

    public void setAirlineAdminId(Integer airlineAdminId) {
        this.airlineAdminId = airlineAdminId;
    }

    public String getAirlineAdminName() {
        return airlineAdminName;
    }

    public void setAirlineAdminName(String airlineAdminName) {
        this.airlineAdminName = airlineAdminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAirlineEmail() {
        return airlineEmail;
    }

    public void setAirlineEmail(String airlineEmail) {
        this.airlineEmail = airlineEmail;
    }

    public List<Flights> getFlights() {
        return flights;
    }

    public void setFlights(List<Flights> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "AirLineAdmin [airlineAdminId=" + airlineAdminId +
               ", airlineAdminName=" + airlineAdminName +
               ", contactNumber=" + contactNumber +
               ", airlineEmail=" + airlineEmail + "]";
    }
}
