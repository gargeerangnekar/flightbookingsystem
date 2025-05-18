package com.capgemini.flightbookingsystem.dto;

import java.time.LocalDateTime;

public class FlightBookingDto {

    private Integer departureAirportId;
    private Integer arrivalAirportId;
    private String departureAirportName;
    private String arrivalAirportName;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double amount;

    // Default constructor
    public FlightBookingDto() {}

    // Private constructor for Builder
    private FlightBookingDto(Builder builder) {
        this.departureAirportId = builder.departureAirportId;
        this.arrivalAirportId = builder.arrivalAirportId;
        this.departureAirportName = builder.departureAirportName;
        this.arrivalAirportName = builder.arrivalAirportName;
        this.departureCity = builder.departureCity;
        this.arrivalCity = builder.arrivalCity;
        this.departureTime = builder.departureTime;
        this.arrivalTime = builder.arrivalTime;
        this.amount = builder.amount;
    }

    // Builder class
    public static class Builder {
        private Integer departureAirportId;
        private Integer arrivalAirportId;
        private String departureAirportName;
        private String arrivalAirportName;
        private String departureCity;
        private String arrivalCity;
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
        private Double amount;

        public Builder departureAirportId(Integer id) {
            this.departureAirportId = id;
            return this;
        }

        public Builder arrivalAirportId(Integer id) {
            this.arrivalAirportId = id;
            return this;
        }

        public Builder departureAirportName(String name) {
            this.departureAirportName = name;
            return this;
        }

        public Builder arrivalAirportName(String name) {
            this.arrivalAirportName = name;
            return this;
        }

        public Builder departureCity(String city) {
            this.departureCity = city;
            return this;
        }

        public Builder arrivalCity(String city) {
            this.arrivalCity = city;
            return this;
        }

        public Builder departureTime(LocalDateTime time) {
            this.departureTime = time;
            return this;
        }

        public Builder arrivalTime(LocalDateTime time) {
            this.arrivalTime = time;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public FlightBookingDto build() {
            return new FlightBookingDto(this);
        }
    }

    // Getters and Setters
    public Integer getDepartureAirportId() { return departureAirportId; }
    public void setDepartureAirportId(Integer id) { this.departureAirportId = id; }

    public Integer getArrivalAirportId() { return arrivalAirportId; }
    public void setArrivalAirportId(Integer id) { this.arrivalAirportId = id; }

    public String getDepartureAirportName() { return departureAirportName; }
    public void setDepartureAirportName(String name) { this.departureAirportName = name; }

    public String getArrivalAirportName() { return arrivalAirportName; }
    public void setArrivalAirportName(String name) { this.arrivalAirportName = name; }

    public String getDepartureCity() { return departureCity; }
    public void setDepartureCity(String city) { this.departureCity = city; }

    public String getArrivalCity() { return arrivalCity; }
    public void setArrivalCity(String city) { this.arrivalCity = city; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime time) { this.departureTime = time; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime time) { this.arrivalTime = time; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "FlightBookingDto{" +
                "departureAirportId=" + departureAirportId +
                ", arrivalAirportId=" + arrivalAirportId +
                ", departureAirportName='" + departureAirportName + '\'' +
                ", arrivalAirportName='" + arrivalAirportName + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", arrivalCity='" + arrivalCity + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", amount=" + amount +
                '}';
    }
}
