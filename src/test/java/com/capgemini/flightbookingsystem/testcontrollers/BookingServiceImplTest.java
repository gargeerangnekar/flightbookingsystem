package com.capgemini.flightbookingsystem.testcontrollers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.entities.User;
import com.capgemini.flightbookingsystem.exceptions.BookingNotFoundException;
import com.capgemini.flightbookingsystem.repositories.BookingRepository;
import com.capgemini.flightbookingsystem.repositories.FlightRepository;
import com.capgemini.flightbookingsystem.repositories.UserRepository;
import com.capgemini.flightbookingsystem.services.BookingServiceImpl;

class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private Booking booking1;
    private Booking booking2;
    private User user;
    private Flights flight;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUserId(1);

        flight = new Flights();
        flight.setFlightId(100);

        booking1 = new Booking();
        booking1.setBookingId(1);
        booking1.setSeatNumber("A1");
        booking1.setSeatClass("Economy");
        booking1.setBookingTime(LocalDateTime.now());
        booking1.setAmount(1000.0);
        booking1.setUsers(user);
        booking1.setFlights(flight);

        booking2 = new Booking();
        booking2.setBookingId(2);
        booking2.setSeatNumber("B2");
        booking2.setSeatClass("Business");
        booking2.setBookingTime(LocalDateTime.now());
        booking2.setAmount(2000.0);
        booking2.setUsers(user);
        booking2.setFlights(flight);
    }

    @Test
    @DisplayName("Should save a booking successfully")
    void testSaveBooking() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(flightRepository.findById(100)).thenReturn(Optional.of(flight));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Booking saved = bookingService.saveBooking(booking1);

        assertNotNull(saved);
        assertEquals(1000.0, saved.getAmount());
        assertNotNull(saved.getPayment());
        assertEquals(user, saved.getUsers());
        assertEquals(flight, saved.getFlights());

        verify(bookingRepository).save(saved);
    }

    @Test
    @DisplayName("Should fetch a booking by ID successfully")
    void testGetBookingById() {
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking1));

        Booking found = bookingService.getBookingById(1);

        assertEquals(booking1, found);
    }

    @Test
    @DisplayName("Should throw BookingNotFoundException when booking not found")
    void testGetBookingByIdNotFound() {
        when(bookingRepository.findById(10)).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, () -> bookingService.getBookingById(10));
    }

    @Test
    @DisplayName("Should fetch all bookings")
    void testGetAllBookings() {
        List<Booking> bookings = Arrays.asList(booking1, booking2);
        when(bookingRepository.findAll()).thenReturn(bookings);

        List<Booking> result = bookingService.getAllBookings();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should update booking successfully")
    void testUpdateBooking() {
        Booking updateInfo = new Booking();
        updateInfo.setAmount(3000.0);
        updateInfo.setBookingTime(LocalDateTime.now().plusDays(1));
        updateInfo.setSeatClass("Business");
        updateInfo.setSeatNumber("C3");
        updateInfo.setUsers(user);
        updateInfo.setFlights(flight);

        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking1));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Booking updated = bookingService.updateBooking(1, updateInfo);

        assertEquals(3000.0, updated.getAmount());
        assertEquals("Business", updated.getSeatClass());
        assertEquals("C3", updated.getSeatNumber());
    }

    @Test
    @DisplayName("Should throw BookingNotFoundException on update if booking not found")
    void testUpdateBookingNotFound() {
        when(bookingRepository.findById(10)).thenReturn(Optional.empty());

        Booking updateInfo = new Booking();

        assertThrows(BookingNotFoundException.class, () -> bookingService.updateBooking(10, updateInfo));
    }

    @Test
    @DisplayName("Should delete booking successfully")
    void testDeleteBooking() {
        when(bookingRepository.findById(1)).thenReturn(Optional.of(booking1));
        doNothing().when(bookingRepository).delete(booking1);

        bookingService.deleteBooking(1);

        verify(bookingRepository).delete(booking1);
    }

    @Test
    @DisplayName("Should throw BookingNotFoundException on delete if booking not found")
    void testDeleteBookingNotFound() {
        when(bookingRepository.findById(10)).thenReturn(Optional.empty());

        assertThrows(BookingNotFoundException.class, () -> bookingService.deleteBooking(10));

        verify(bookingRepository, never()).delete(any());
    }
}
