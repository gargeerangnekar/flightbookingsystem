package com.capgemini.flightbookingsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.services.FlightService;

@Controller
@RequestMapping("/flights")
public class FlightMvcController {
	
	//Injecting service layer
	FlightService flightService;

	@Autowired
	public FlightMvcController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping
	public String listFlights(Model model) {
		model.addAttribute("flights", flightService.getAllFlights());
		return "flights/list";
	}
	
	@GetMapping("/{flightId}")
	public String listFlightById(@PathVariable Integer flightId,Model model) {
		model.addAttribute("flight", flightService.getFlightById(flightId));
		return "flights/detail";
	}
	
	@GetMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
        return "redirect:/flights";
    }
	
}
