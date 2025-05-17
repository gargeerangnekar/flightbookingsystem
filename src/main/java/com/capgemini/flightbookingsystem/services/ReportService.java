package com.capgemini.flightbookingsystem.services;

import java.time.LocalDate;

import com.capgemini.flightbookingsystem.dto.FlightSystemReportDto;

public interface ReportService {
	
	    FlightSystemReportDto generateFlightSystemReport(LocalDate startDate, LocalDate endDate);


}
