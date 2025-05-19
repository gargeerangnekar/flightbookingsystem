package com.capgemini.flightbookingsystem.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.capgemini.flightbookingsystem.entities.Airport;
import com.capgemini.flightbookingsystem.exceptions.AirportNotFoundException;
import com.capgemini.flightbookingsystem.repositories.AirportRepository;

@Service
public class AirportServiceImpl implements AirportService {

    private static final Logger logger = LoggerFactory.getLogger(AirportServiceImpl.class);

    private final AirportRepository airportRepository;

    // Constructor injection
    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public Airport saveAirport(Airport airport) {
        logger.info("Saving new airport: {}", airport);
        Airport saved = airportRepository.save(airport);
        logger.debug("Airport saved with ID: {}", saved.getAirportId());
        return saved;
    }

    @Override
    public Airport getAirportById(Integer airportId) {
        logger.info("Fetching airport with ID: {}", airportId);
        return airportRepository.findById(airportId)
                .orElseThrow(() -> {
                    logger.warn("Airport not found with ID: {}", airportId);
                    return new AirportNotFoundException("Airport ID not found: " + airportId);
                });
    }

    @Override
    public List<Airport> getAllAirports() {
        logger.info("Fetching all airports");
        List<Airport> airports = airportRepository.findAll();
        logger.debug("Total airports found: {}", airports.size());
        return airports;
    }

    @Override
    public Airport updateAirport(Airport airport, Integer airportId) {
        logger.info("Updating airport with ID: {}", airport.getAirportId());

        Airport existingAirport = airportRepository.findById(airport.getAirportId())
                .orElseThrow(() -> {
                    logger.warn("Cannot update. Airport not found with ID: {}", airport.getAirportId());
                    return new AirportNotFoundException("Airport ID not found: " + airport.getAirportId());
                });

        if (airport.getAirportName() != null) {
            existingAirport.setAirportName(airport.getAirportName());
        }
        if (airport.getCity() != null) {
            existingAirport.setCity(airport.getCity());
        }
        if (airport.getContact() != null) {
            existingAirport.setContact(airport.getContact());
        }

        Airport updated = airportRepository.save(existingAirport);
        logger.debug("Airport updated: {}", updated);
        return updated;
    }

    @Override
    public void deleteAirport(Integer airportId) {
        logger.info("Deleting airport with ID: {}", airportId);

        if (!airportRepository.existsById(airportId)) {
            logger.warn("Cannot delete. Airport not found with ID: {}", airportId);
            throw new AirportNotFoundException("Airport ID not found: " + airportId);
        }

        airportRepository.deleteById(airportId);
        logger.info("Airport with ID {} deleted successfully", airportId);
    }
}
