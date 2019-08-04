package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.dto.flight.FlightFiltersDto;
import com.kodilla.backend.service.FlightService;
import org.junit.Test;

import com.google.gson.Gson;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService service;

    @Test
    public void getFlights() throws Exception{
        //Given
        LocalDateTime dateTime = LocalDateTime.now();
        List<FlightDto> list = new ArrayList<>();
        FlightDto flightDto = new FlightDto(1, dateTime, "origin", "destination");
        list.add(flightDto);
        when(service.getFlights("Berlin", "New York", "")).thenReturn(list);
        //When & then
        mockMvc.perform(get("/v1/flights").contentType(MediaType.APPLICATION_JSON)
                .param("originPlace", "Berlin")
                .param("destinationPlace", "New York")
                .param("outboundPartialDate", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].origin", is("origin")));

    }

    @Test
    public void getFilteredFlights() throws Exception{
        //Given
        LocalDateTime dateTime = LocalDateTime.now();
        List<FlightDto> list = new ArrayList<>();
        FlightDto flightDto = new FlightDto(1, dateTime, "origin", "destination");
        list.add(flightDto);
        when(service.getFilteredFlights(1, "class", 0, 500)).thenReturn(list);
        //When & then
        mockMvc.perform(get("/v1/flights/filter/1/").contentType(MediaType.APPLICATION_JSON)
                .param("carrierClass", "class")
                .param("priceMoreThan", "0")
                .param("priceLessThan", "500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].origin", is("origin")));
    }

    @Test
    public void getFlightsLocation() throws Exception{
        //Given
        when(service.getFlightsLocation("Berlin")).thenReturn("BER");
        //When & Then
        mockMvc.perform(get("/v1/flights/locations/Berlin").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("BER")));
    }

    @Test
    public void getHotelSearchHistory() throws Exception{
        //Given
        LocalDateTime dateTime = LocalDateTime.now();
        List<FlightDto> list = new ArrayList<>();
        FlightDto flightDto = new FlightDto(1, dateTime, "origin", "destination");
        list.add(flightDto);
        when(service.getHotelSearchHistory()).thenReturn(list);
        //When & then
        mockMvc.perform(get("/v1/flights/history").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].origin", is("origin")));
    }

    @Test
    public void saveFlightFilters() throws Exception{
        //Given
        FlightFiltersDto filtersDto = new FlightFiltersDto("class", new BigDecimal(0), new BigDecimal(500));
        Gson gson = new Gson();
        String json = gson.toJson(filtersDto);
        //When & then
        mockMvc.perform(post("/v1/flights/filter").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }
}