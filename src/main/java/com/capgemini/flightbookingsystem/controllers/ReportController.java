package com.capgemini.flightbookingsystem.controllers;

import com.capgemini.flightbookingsystem.dto.FlightSystemReportDto;
import com.capgemini.flightbookingsystem.services.ReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/flight-system")
    public ResponseEntity<FlightSystemReportDto> generateFlightSystemReport(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        
        // Set default dates if not provided (last 30 days)
        LocalDate end = endDate != null ? endDate : LocalDate.now();
        LocalDate start = startDate != null ? startDate : end.minusDays(30);
        
        // Validate date range
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date");
        }

        // Validate dates aren't in the future
        if (end.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("End date cannot be in the future");
        }

        // Maximum report range (e.g., 1 year)
        if (start.isBefore(end.minusYears(1))) {
            throw new IllegalArgumentException("Report period cannot exceed 1 year");
        }

        FlightSystemReportDto report = reportService.generateFlightSystemReport(start, end);
        
        // Set formatted date strings in the response
        report.setStartDate(start.format(dateFormatter));
        report.setEndDate(end.format(dateFormatter));
        
        return ResponseEntity.ok(report);
    }
}