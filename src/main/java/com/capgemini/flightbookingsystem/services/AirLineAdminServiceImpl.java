package com.capgemini.flightbookingsystem.services;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;
import com.capgemini.flightbookingsystem.exceptions.AirlineAdminNotFoundException;
import com.capgemini.flightbookingsystem.repositories.AirLineAdminRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AirLineAdminServiceImpl implements AirLineAdminService {

	private final AirLineAdminRepository airLineAdminRepository;
	PasswordEncoder passwordEncoder;

	@Autowired
	public AirLineAdminServiceImpl(AirLineAdminRepository airLineAdminRepository, PasswordEncoder passwordEncoder) {
		this.airLineAdminRepository = airLineAdminRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<AirLineAdmin> getAllAirlineAdmins() {
		return airLineAdminRepository.findAll();
	}

	@Override
	public AirLineAdmin getAirlineAdminById(Integer id) {
		return airLineAdminRepository.findById(id)
				.orElseThrow(() -> new AirlineAdminNotFoundException("Admin not found with ID: " + id));
	}

	@Override
	public AirLineAdmin createAdmin(AirLineAdmin admin) {
		String encodedPassword = passwordEncoder.encode(admin.getPassword());
	    admin.setPassword(encodedPassword);
	    return airLineAdminRepository.save(admin);
	}

	@Override
	public AirLineAdmin updateAdmin(Integer id, AirLineAdmin admin) {
		AirLineAdmin existingAdmin = airLineAdminRepository.findById(id)
				.orElseThrow(() -> new AirlineAdminNotFoundException("Admin not found with ID: " + id));

		// Update fields
		existingAdmin.setAirlineAdminName(admin.getAirlineAdminName());
		existingAdmin.setPassword(admin.getPassword());
		existingAdmin.setContactNumber(admin.getContactNumber());
		existingAdmin.setAirlineEmail(admin.getAirlineEmail());
		// Optionally update other properties

		return airLineAdminRepository.save(existingAdmin);
	}

	@Override
	public void deleteAdmin(Integer id) {
		AirLineAdmin existingAdmin = airLineAdminRepository.findById(id)
				.orElseThrow(() -> new AirlineAdminNotFoundException("Admin not found with ID: " + id));
		airLineAdminRepository.delete(existingAdmin);
	}

	@Override
	public boolean existsByAirlineEmail(String email) {
		return airLineAdminRepository.existsByAirlineEmail(email);
	}

	@Override
	public boolean existsByContactNumber(String contactNumber) {
		return airLineAdminRepository.existsByContactNumber(contactNumber);
	}

	@Override
	public AirLineAdmin findByAirlineEmail(String email) {
		return airLineAdminRepository.findByAirlineEmail(email)
				.orElseThrow(() -> new AirlineAdminNotFoundException("User doesn't exist with email :" + email));
	}

}
