package com.capgemini.flightbookingsystem.services;

import com.capgemini.flightbookingsystem.dto.FlightSystemReportDto;
import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.entities.Airport; // Assuming you have an Airport entity
import com.capgemini.flightbookingsystem.repositories.BookingRepository;
import com.capgemini.flightbookingsystem.repositories.FlightRepository;
import com.capgemini.flightbookingsystem.repositories.AirportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository; // Add this repository

    public ReportServiceImpl(BookingRepository bookingRepository, 
                           FlightRepository flightRepository,
                           AirportRepository airportRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public FlightSystemReportDto generateFlightSystemReport(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        // Get all bookings in date range
        List<Booking> bookings = bookingRepository.findByBookingTimeBetween(startDateTime, endDateTime);
        
        // Get all flights with bookings in date range
        List<Flights> flights = flightRepository.findFlightsWithBookingsBetweenDates(startDateTime, endDateTime);

        // Pre-fetch all airports to avoid N+1 query problem
        Map<Integer, Airport> airportsMap = airportRepository.findAll().stream()
                .collect(Collectors.toMap(Airport::getAirportId, airport -> airport));

        FlightSystemReportDto report = new FlightSystemReportDto();
        report.setStartDate(startDate.toString());
        report.setEndDate(endDate.toString());
        
        // Set summary statistics
        report.setTotalBookings(bookings.size());
        report.setTotalFlights(flights.size());
        report.setTotalRevenue(bookings.stream().mapToDouble(Booking::getAmount).sum());

        // Prepare bookings by date data
        report.setBookingsByDate(
            bookings.stream()
                .collect(Collectors.groupingBy(
                    b -> b.getBookingTime().toLocalDate().toString(),
                    Collectors.counting()
                ))
        );

        // Prepare revenue by flight data
        report.setRevenueByFlight(
            flights.stream()
                .map(f -> {
                    FlightSystemReportDto.FlightRevenueDto dto = new FlightSystemReportDto.FlightRevenueDto();
                    dto.setFlightNumber(f.getFlightNumber());
                    double revenue = bookings.stream()
                        .filter(b -> b.getFlights().getFlightId().equals(f.getFlightId()))
                        .mapToDouble(Booking::getAmount)
                        .sum();
                    dto.setRevenue(revenue);
                    dto.setPercentage((revenue / report.getTotalRevenue()) * 100);
                    return dto;
                })
                .collect(Collectors.toList())
        );

        // Prepare bookings per flight data
        report.setBookingsPerFlight(
            flights.stream()
                .map(f -> {
                    FlightSystemReportDto.FlightOccupancyDto dto = new FlightSystemReportDto.FlightOccupancyDto();
                    dto.setFlightNumber(f.getFlightNumber());
                    
                    // Get airport names from the map
                    String departureName = airportsMap.get(f.getDepartureAirportId()).getAirportName();
                    String arrivalName = airportsMap.get(f.getArrivalAirportId()).getAirportName();
                    dto.setRoute(departureName + " → " + arrivalName);
                    
                    long bookingCount = bookings.stream()
                        .filter(b -> b.getFlights().getFlightId().equals(f.getFlightId()))
                        .count();
                    dto.setBookingCount((int) bookingCount);
                    
                    // Calculate total seats - this depends on your data model
                    // Option 1: If you have a relationship to aircraft which has seats
                    // int totalSeats = f.getAircraft().getTotalSeats();
                    // Option 2: If you have a seats table with flight_id foreign key
                    // int totalSeats = seatRepository.countByFlightId(f.getFlightId());
                    // Option 3: If you have a fixed number per flight class
                    // int totalSeats = 180; // example fixed value
                    // For now, I'll use a default value - you should replace this with actual logic
                    int totalSeats = 180; // Replace with your actual logic to get total seats
                    
                    double occupancyRate = (bookingCount * 100.0) / totalSeats;
                    dto.setOccupancyRate(occupancyRate);
                    return dto;
                })
                .collect(Collectors.toList())
        );

        // Prepare revenue details
        report.setRevenueDetails(
            flights.stream()
                .map(f -> {
                    FlightSystemReportDto.FlightRevenueDetailDto dto = new FlightSystemReportDto.FlightRevenueDetailDto();
                    dto.setFlightNumber(f.getFlightNumber());
                    dto.setDepartureTime(f.getDepartureTime());
                    
                    List<Booking> flightBookings = bookings.stream()
                        .filter(b -> b.getFlights().getFlightId().equals(f.getFlightId()))
                        .collect(Collectors.toList());
                    
                    double revenue = flightBookings.stream()
                        .mapToDouble(Booking::getAmount)
                        .sum();
                    dto.setRevenue(revenue);
                    
                    if (!flightBookings.isEmpty()) {
                        dto.setAvgFare(revenue / flightBookings.size());
                    } else {
                        dto.setAvgFare(0);
                    }
                    
                    dto.setRevenuePercentage((revenue / report.getTotalRevenue()) * 100);
                    return dto;
                })
                .collect(Collectors.toList())
        );

        // Prepare detailed bookings
        report.setDetailedBookings(
            bookings.stream()
                .map(b -> {
                    FlightSystemReportDto.DetailedBookingDto dto = new FlightSystemReportDto.DetailedBookingDto();
                    dto.setBookingId(b.getBookingId().toString());
                    dto.setPassengerName(b.getUsers().getName());
                    dto.setFlightNumber(b.getFlights().getFlightNumber());
                    dto.setDepartureTime(b.getFlights().getDepartureTime());
                    dto.setArrivalTime(b.getFlights().getArrivalTime());
                    dto.setSeatClass(b.getSeatClass());
                    dto.setAmount(b.getAmount());
                    dto.setStatus(b.getFlights().getStatus());
                    
                    // Add airport names to detailed booking
                    Flights flight = b.getFlights();
                    String departureName = airportsMap.get(flight.getDepartureAirportId()).getAirportName();
                    String arrivalName = airportsMap.get(flight.getArrivalAirportId()).getAirportName();
                    dto.setDepartureAirport(departureName);
                    dto.setArrivalAirport(arrivalName);
                    
                    return dto;
                })
                .collect(Collectors.toList())
        );

        return report;
    }
}