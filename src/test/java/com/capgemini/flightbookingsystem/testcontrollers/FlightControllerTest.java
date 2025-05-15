package com.capgemini.flightbookingsystem.testcontrollers;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.capgemini.flightbookingsystem.controllers.FlightRestController;
import com.capgemini.flightbookingsystem.entities.Flights;
import com.capgemini.flightbookingsystem.services.FlightService;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;

@WebMvcTest(FlightRestController.class)
@AutoConfigureMockMvc
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;
    
    //1. Test Case for POST Mapping
    @Test
    @DisplayName("Test to create a flight")
    void testCreateFlight() throws Exception {
        Flights flight = new Flights(
                "AI202",
                LocalDateTime.parse("2025-06-01T10:30:00"),
                LocalDateTime.parse("2025-06-01T13:45:00"),
                "Scheduled",
                "Boeing 737",
                180,
                1,
                3
        );

        Mockito.when(flightService.createNewFlight(Mockito.any())).thenReturn(flight);

        mockMvc.perform(post("/api/flights")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "flightNumber": "AI202",
                          "departureTime": "2025-06-01T10:30:00",
                          "arrivalTime": "2025-06-01T13:45:00",
                          "status": "Scheduled",
                          "aircraftModel": "Boeing 737",
                          "capacity": 180,
                          "airlineAdminId": 1,
                          "arrivalAirportId": 3,
                          "departureAirportId": 2
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flightNumber").value("AI202"))
                .andExpect(jsonPath("$.departureTime").value("2025-06-01T10:30:00"))
                .andExpect(jsonPath("$.arrivalTime").value("2025-06-01T13:45:00"))
                .andExpect(jsonPath("$.status").value("Scheduled"))
                .andExpect(jsonPath("$.aircraftModel").value("Boeing 737"))
                .andExpect(jsonPath("$.capacity").value(180))
                .andExpect(jsonPath("$.arrivalAirportId").value(3))
                .andExpect(jsonPath("$.departureAirportId").value(2))
                .andDo(MockMvcResultHandlers.print());
    }
    
    
    //2. Test Case for GET Mapping
    @Test
    @DisplayName("Test to get a flight by ID")
    void testGetFlightById() throws Exception {
        Flights flight = new Flights(
                "AI202",
                LocalDateTime.parse("2025-06-01T10:30:00"),
                LocalDateTime.parse("2025-06-01T13:45:00"),
                "Scheduled",
                "Boeing 737",
                180,
                1,
                3
        );

        Mockito.when(flightService.getFlightById(1)).thenReturn(flight);

        mockMvc.perform(get("/api/flights/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightNumber").value("AI202"))
                .andExpect(jsonPath("$.capacity").value(180))
                .andDo(MockMvcResultHandlers.print());
    }
    
    
    //3. Test Case for PUT Mapping
    @Test
    @DisplayName("Test to update a flight")
    void testUpdateFlight() throws Exception {
        Flights updatedFlight = new Flights(
                "AI202",
                LocalDateTime.parse("2025-06-01T10:30:00"),
                LocalDateTime.parse("2025-06-01T13:45:00"),
                "Delayed",
                "Boeing 737",
                180,
                1,
                3
        );

        Mockito.when(flightService.updateFlightById(Mockito.eq(1), Mockito.any())).thenReturn(updatedFlight);

        mockMvc.perform(put("/api/flights/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "flightNumber": "AI202",
                          "departureTime": "2025-06-01T10:30:00",
                          "arrivalTime": "2025-06-01T13:45:00",
                          "status": "Delayed",
                          "aircraftModel": "Boeing 737",
                          "capacity": 180,
                          "airlineAdminId": 1,
                          "arrivalAirportId": 3,
                          "departureAirportId": 2
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Delayed"))
                .andDo(MockMvcResultHandlers.print());
    }
    
    
    //4. Test Case for DELETE Mapping
    @Test
    @DisplayName("Test to delete a flight by ID")
    void testDeleteFlight() throws Exception {
        Mockito.doNothing().when(flightService).deleteFlight(1);

        mockMvc.perform(delete("/api/flights/1"))
                .andExpect(status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }



}

