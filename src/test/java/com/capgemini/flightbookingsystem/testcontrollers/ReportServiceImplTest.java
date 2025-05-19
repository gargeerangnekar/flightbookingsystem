package com.capgemini.flightbookingsystem.testcontrollers;

import com.capgemini.flightbookingsystem.dto.FlightSystemReportDto;
import com.capgemini.flightbookingsystem.entities.*;
import com.capgemini.flightbookingsystem.repositories.*;
import com.capgemini.flightbookingsystem.services.ReportServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    private Booking sampleBooking;
    private Flights sampleFlight;
    private Airport departureAirport;
    private Airport arrivalAirport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup sample airport
        departureAirport = new Airport();
        departureAirport.setAirportId(1);
        departureAirport.setAirportName("Mumbai");

        arrivalAirport = new Airport();
        arrivalAirport.setAirportId(2);
        arrivalAirport.setAirportName("Delhi");

        // Setup sample flight
        sampleFlight = new Flights();
        sampleFlight.setFlightId(101);
        sampleFlight.setFlightNumber("AI202");
        sampleFlight.setDepartureAirportId(1);
        sampleFlight.setArrivalAirportId(2);
        sampleFlight.setDepartureTime(LocalDateTime.of(2025, 5, 18, 10, 0));
        sampleFlight.setArrivalTime(LocalDateTime.of(2025, 5, 18, 12, 0));

        // Setup sample booking
        sampleBooking = new Booking();
        sampleBooking.setBookingId(1);
        sampleBooking.setBookingTime(LocalDateTime.of(2025, 5, 10, 9, 0));
        sampleBooking.setAmount(4500.0);
        sampleBooking.setFlights(sampleFlight);

        User user = new User();
        user.setName("John Doe");
        sampleBooking.setUsers(user);
        sampleBooking.setSeatClass("Economy");
    }

    @Test
    void testGenerateFlightSystemReport() {
        LocalDate start = LocalDate.of(2025, 5, 1);
        LocalDate end = LocalDate.of(2025, 5, 20);

        when(bookingRepository.findByBookingTimeBetween(any(), any()))
                .thenReturn(List.of(sampleBooking));

        when(flightRepository.findFlightsWithBookingsBetweenDates(any(), any()))
                .thenReturn(List.of(sampleFlight));

        when(airportRepository.findAll())
                .thenReturn(List.of(departureAirport, arrivalAirport));

        FlightSystemReportDto report = reportService.generateFlightSystemReport(start, end);

        assertNotNull(report);
        assertEquals("2025-05-01", report.getStartDate());
        assertEquals("2025-05-20", report.getEndDate());
        assertEquals(1, report.getTotalBookings());
        assertEquals(1, report.getTotalFlights());
        assertEquals(4500.0, report.getTotalRevenue());

        // Check one revenue item
        assertFalse(report.getRevenueByFlight().isEmpty());
        assertEquals("AI202", report.getRevenueByFlight().get(0).getFlightNumber());

        // Check detailed booking info
        assertFalse(report.getDetailedBookings().isEmpty());
        assertEquals("John Doe", report.getDetailedBookings().get(0).getPassengerName());
        assertEquals("Mumbai", report.getDetailedBookings().get(0).getDepartureAirport());
        assertEquals("Delhi", report.getDetailedBookings().get(0).getArrivalAirport());
    }
}
