package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.facade.HotelFacade;
import com.kodilla.backend.service.FlightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HolidayController.class)
public class HolidayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelFacade hotelFacade;

    @MockBean
    private FlightService flightService;

    @Test
    public void getHoliday() throws Exception {
        //Given
        List<HotelDto> hotelList = new ArrayList<>();
        HotelDto hotelDto = new HotelDto();
        hotelList.add(hotelDto);
        when(hotelFacade.getHotels(1, "New York", "", "", 1)).thenReturn(hotelList);
        List<FlightDto> flightList = new ArrayList<>();
        FlightDto flightDto = new FlightDto();
        flightList.add(flightDto);
        when(flightService.getFlights("Berlin", "New York", "")).thenReturn(flightList);
        when(flightService.getFlights("New York", "Berlin", "")).thenReturn(flightList);

        //When & then
        mockMvc.perform(get("/v1/holiday").contentType(MediaType.APPLICATION_JSON)
                .param("rooms", "1")
                .param("originPlace", "Berlin")
                .param("destinationPlace", "New York")
                .param("checkin", "")
                .param("checkout", "")
                .param("adults", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hotels", hasSize(1)))
                .andExpect(jsonPath("$.tripFlights", hasSize(1)))
                .andExpect(jsonPath("$.returnFlights", hasSize(1)));
    }
}