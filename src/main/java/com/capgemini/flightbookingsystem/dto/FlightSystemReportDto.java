package com.capgemini.flightbookingsystem.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FlightSystemReportDto {
    private String startDate;
    private String endDate;
    private long totalBookings;
    private long totalFlights;
    private double totalRevenue;
    
    // For bookings by date chart
    private Map<String, Long> bookingsByDate;
    
    // For revenue by flight chart
    private List<FlightRevenueDto> revenueByFlight;
    
    // For bookings per flight table
    private List<FlightOccupancyDto> bookingsPerFlight;
    
    // For revenue details table
    private List<FlightRevenueDetailDto> revenueDetails;
    
    // For detailed booking report
    private List<DetailedBookingDto> detailedBookings;
    
    // Getters and setters
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public long getTotalBookings() {
        return totalBookings;
    }
    
    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }
    
    public long getTotalFlights() {
        return totalFlights;
    }
    
    public void setTotalFlights(long totalFlights) {
        this.totalFlights = totalFlights;
    }
    
    public double getTotalRevenue() {
        return totalRevenue;
    }
    
    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    
    public Map<String, Long> getBookingsByDate() {
        return bookingsByDate;
    }
    
    public void setBookingsByDate(Map<String, Long> bookingsByDate) {
        this.bookingsByDate = bookingsByDate;
    }
    
    public List<FlightRevenueDto> getRevenueByFlight() {
        return revenueByFlight;
    }
    
    public void setRevenueByFlight(List<FlightRevenueDto> revenueByFlight) {
        this.revenueByFlight = revenueByFlight;
    }
    
    public List<FlightOccupancyDto> getBookingsPerFlight() {
        return bookingsPerFlight;
    }
    
    public void setBookingsPerFlight(List<FlightOccupancyDto> bookingsPerFlight) {
        this.bookingsPerFlight = bookingsPerFlight;
    }
    
    public List<FlightRevenueDetailDto> getRevenueDetails() {
        return revenueDetails;
    }
    
    public void setRevenueDetails(List<FlightRevenueDetailDto> revenueDetails) {
        this.revenueDetails = revenueDetails;
    }
    
    public List<DetailedBookingDto> getDetailedBookings() {
        return detailedBookings;
    }
    
    public void setDetailedBookings(List<DetailedBookingDto> detailedBookings) {
        this.detailedBookings = detailedBookings;
    }

    // Nested DTO classes
    public static class FlightRevenueDto {
        private String flightNumber;
        private double revenue;
        private double percentage;
        
        // Getters and setters
        public String getFlightNumber() {
            return flightNumber;
        }
        
        public void setFlightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
        }
        
        public double getRevenue() {
            return revenue;
        }
        
        public void setRevenue(double revenue) {
            this.revenue = revenue;
        }
        
        public double getPercentage() {
            return percentage;
        }
        
        public void setPercentage(double percentage) {
            this.percentage = percentage;
        }
    }

    public static class FlightOccupancyDto {
        private String flightNumber;
        private String route;
        private int bookingCount;
        private double occupancyRate;
        
        // Getters and setters
        public String getFlightNumber() {
            return flightNumber;
        }
        
        public void setFlightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
        }
        
        public String getRoute() {
            return route;
        }
        
        public void setRoute(String route) {
            this.route = route;
        }
        
        public int getBookingCount() {
            return bookingCount;
        }
        
        public void setBookingCount(int bookingCount) {
            this.bookingCount = bookingCount;
        }
        
        public double getOccupancyRate() {
            return occupancyRate;
        }
        
        public void setOccupancyRate(double occupancyRate) {
            this.occupancyRate = occupancyRate;
        }
    }

    public static class FlightRevenueDetailDto {
        private String flightNumber;
        private LocalDateTime departureTime;
        private double avgFare;
        private double revenue;
        private double revenuePercentage;
        
        // Getters and setters
        public String getFlightNumber() {
            return flightNumber;
        }
        
        public void setFlightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
        }
        
        public LocalDateTime getDepartureTime() {
            return departureTime;
        }
        
        public void setDepartureTime(LocalDateTime departureTime) {
            this.departureTime = departureTime;
        }
        
        public double getAvgFare() {
            return avgFare;
        }
        
        public void setAvgFare(double avgFare) {
            this.avgFare = avgFare;
        }
        
        public double getRevenue() {
            return revenue;
        }
        
        public void setRevenue(double revenue) {
            this.revenue = revenue;
        }
        
        public double getRevenuePercentage() {
            return revenuePercentage;
        }
        
        public void setRevenuePercentage(double revenuePercentage) {
            this.revenuePercentage = revenuePercentage;
        }
    }

    public static class DetailedBookingDto {
        private String bookingId;
        private String passengerName;
        private String flightNumber;
        private String departureAirport;
        private LocalDateTime departureTime;
        private String arrivalAirport;
        private LocalDateTime arrivalTime;
        private String seatClass;
        private double amount;
        private String status;
        
        // Getters and setters
        public String getBookingId() {
            return bookingId;
        }
        
        public void setBookingId(String bookingId) {
            this.bookingId = bookingId;
        }
        
        public String getPassengerName() {
            return passengerName;
        }
        
        public void setPassengerName(String passengerName) {
            this.passengerName = passengerName;
        }
        
        public String getFlightNumber() {
            return flightNumber;
        }
        
        public void setFlightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
        }
        
        public String getDepartureAirport() {
            return departureAirport;
        }
        
        public void setDepartureAirport(String departureAirport) {
            this.departureAirport = departureAirport;
        }
        
        public LocalDateTime getDepartureTime() {
            return departureTime;
        }
        
        public void setDepartureTime(LocalDateTime departureTime) {
            this.departureTime = departureTime;
        }
        
        public String getArrivalAirport() {
            return arrivalAirport;
        }
        
        public void setArrivalAirport(String arrivalAirport) {
            this.arrivalAirport = arrivalAirport;
        }
        
        public LocalDateTime getArrivalTime() {
            return arrivalTime;
        }
        
        public void setArrivalTime(LocalDateTime arrivalTime) {
            this.arrivalTime = arrivalTime;
        }
        
        public String getSeatClass() {
            return seatClass;
        }
        
        public void setSeatClass(String seatClass) {
            this.seatClass = seatClass;
        }
        
        public double getAmount() {
            return amount;
        }
        
        public void setAmount(double amount) {
            this.amount = amount;
        }
        
        public String getStatus() {
            return status;
        }
        
        public void setStatus(String status) {
            this.status = status;
        }
    }
}