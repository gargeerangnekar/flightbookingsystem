package com.capgemini.flightbookingsystem.services;

import com.capgemini.flightbookingsystem.entities.AirLineAdmin;

import java.util.List;
import java.util.Map;

public interface AirLineAdminService {
	List<AirLineAdmin> getAllAirlineAdmins();

	AirLineAdmin getAirlineAdminById(Integer id);

	AirLineAdmin createAdmin(AirLineAdmin admin);

	AirLineAdmin updateAdmin(Integer id, AirLineAdmin admin);

	void deleteAdmin(Integer id);

	boolean existsByAirlineEmail(String email);

	boolean existsByContactNumber(String contactNumber);

	AirLineAdmin findByAirlineEmail(String email);

	AirLineAdmin patchAdmin(Integer adminId, Map<String, Object> updates);
}
