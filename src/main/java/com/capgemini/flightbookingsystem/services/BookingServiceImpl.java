package com.capgemini.flightbookingsystem.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.dto.BookingCardDTO;
import com.capgemini.flightbookingsystem.dto.BookingHistoryDto;
import com.capgemini.flightbookingsystem.entities.Booking;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.entities.Payments;
import com.capgemini.flightbookingsystem.entities.User;
import com.capgemini.flightbookingsystem.exceptions.BookingNotFoundException;
import com.capgemini.flightbookingsystem.exceptions.UserNotFoundException;
import com.capgemini.flightbookingsystem.repositories.BookingRepository;
import com.capgemini.flightbookingsystem.repositories.FlightRepository;
import com.capgemini.flightbookingsystem.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

	BookingRepository bookingRepository;
	UserRepository userRepository;
	FlightRepository flightRepository;

	public BookingServiceImpl(BookingRepository bookingRepository, FlightRepository flightRepository,
			UserRepository userRepository) {
		this.bookingRepository = bookingRepository;
		this.userRepository = userRepository;
		this.flightRepository = flightRepository;
	}

	@Override
	public void deleteBooking(Integer bookingId) {
		log.debug("Deleting booking with ID :", bookingId);
		Booking newBooking = bookingRepository.findById(bookingId).orElseThrow(() -> {
			log.warn("Booking not found with ID :", bookingId);
			return new BookingNotFoundException("Booking not found with ID:" + bookingId);
		});
		log.info("Booking deleted successfully with ID: ", bookingId);
		bookingRepository.delete(newBooking);
	}

	@Override
	public List<Booking> getAllBookings() {
		log.debug("Fetching all bookings from repository");
		return bookingRepository.findAll();
	}

	@Override
	public Booking saveBooking(Booking booking) {
		log.debug("Creating new booking and saving in repository");
		if (booking.getUsers() != null && booking.getUsers().getUserId() != null) {
			User user = userRepository.findById(booking.getUsers().getUserId())
					.orElseThrow(() -> new UserNotFoundException("User not found"));
			booking.setUsers(user);
		}
		if (booking.getFlights() != null && booking.getFlights().getFlightId() != null) {
			Flights flight = flightRepository.findById(booking.getFlights().getFlightId())
					.orElseThrow(() -> new RuntimeException("Flight not found"));
			booking.setFlights(flight);
		}

		Payments payment = new Payments();
		payment.setAmount(booking.getAmount());
		payment.setPaymentDatetime(LocalDateTime.now());
		
		booking.setPayment(payment);
		

		return bookingRepository.save(booking);
	}

	@Override
	public Booking updateBooking(Integer bookingId, Booking booking) {
		log.debug("Updating Booking with ID: {}", bookingId);

		Booking existing = bookingRepository.findById(bookingId).orElseThrow(() -> {
			log.warn("Booking not found with ID: {}", bookingId);
			return new BookingNotFoundException("Booking not found with ID: " + bookingId);
		});

		existing.setAmount(booking.getAmount());
		existing.setBookingTime(booking.getBookingTime());
		existing.setSeatClass(booking.getSeatClass());
		existing.setSeatNumber(booking.getSeatNumber());
		existing.setFlights(booking.getFlights());
		existing.setUsers(booking.getUsers());

		Booking updated = bookingRepository.save(existing);
		log.info("Booking updated successfully with ID: {}", bookingId);
		return updated;
	}

	@Override
	public Booking getBookingById(Integer bookingId) {
		log.debug("Fetching booking with ID: ", bookingId);
		return bookingRepository.findById(bookingId).orElseThrow(() -> {
			log.warn("Booking not found with ID ", bookingId);
			return new BookingNotFoundException("Booking not found with ID :" + bookingId);
		});
	}
	
	@Override
	public BookingCardDTO getBookingCardById(Integer bookingId) {
	    log.debug("Fetching booking with ID: {}", bookingId);
	    Booking booking = bookingRepository.getBookingWithUserAndFlight(bookingId);
	    if (booking == null) {
	        log.warn("Booking not found with ID: {}", bookingId);
	        throw new BookingNotFoundException("Booking not found with ID: " + bookingId);
	    }

	    BookingCardDTO dto = new BookingCardDTO();
	    dto.setBookingId(booking.getBookingId());
	    dto.setPassengerName(booking.getUsers() != null ? booking.getUsers().getName() : "N/A");
	    dto.setPassportNumber(booking.getUsers() != null ? booking.getUsers().getPassportNumber() : "N/A");
	    dto.setFlightNumber(booking.getFlights() != null ? booking.getFlights().getFlightNumber() : "N/A");
	    dto.setAircraftModel(booking.getFlights() != null ? booking.getFlights().getAircraftModel() : "N/A");
	    dto.setDepartureTime(booking.getFlights() != null ? booking.getFlights().getDepartureTime() : null);
	    dto.setArrivalTime(booking.getFlights() != null ? booking.getFlights().getArrivalTime() : null);
	    dto.setBookingTime(booking.getBookingTime());
	    dto.setSeatNumber(booking.getSeatNumber());
	    dto.setSeatClass(booking.getSeatClass());
	    dto.setAmount(booking.getAmount());

	    return dto;
	}


	@Override
	public List<Flights> searchFlights(
			Integer departureAirportId, Integer arrivalAirportId ,LocalDate departureTime) {
		return bookingRepository.searchFlights(departureAirportId, arrivalAirportId, departureTime);
	}
	 @Override
	public List<BookingHistoryDto> getBookingHistoryById(Integer userId) {
		return bookingRepository.getBookingHistory(userId);
	}
	 @Override
	public List<BookingHistoryDto> getAllBookingHistory() {
		return bookingRepository.getAllBookingHistory();
	}
	
	 
	
}