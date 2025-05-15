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

//16
@Entity
@Table(name = "airline_admin")
public class AirLineAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_admin_id")
    private Integer airlineAdminId;

    @Column(name = "airline_admin_name", nullable = false, length = 255)
    private String airlineAdminName;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "contact_number", nullable = false, length = 45)
    private String contactNumber;

    @Column(name = "airline_email", nullable = false, length = 45)
    private String airlineEmail;
    
    @OneToMany(mappedBy = "airlineAdmin", cascade = CascadeType.ALL)
	@JsonManagedReference
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

    @Override
    public String toString() {
        return "AirlinesAdmin [airlineAdminId=" + airlineAdminId +
               ", airlineAdminName=" + airlineAdminName +
               ", contactNumber=" + contactNumber +
               ", airlineEmail=" + airlineEmail + "]";
    }
}
