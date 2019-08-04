package com.kodilla.backend.controller;

import com.google.gson.Gson;
import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelFiltersDto;
import com.kodilla.backend.domain.dto.hotel.HotelLiteDto;
import com.kodilla.backend.facade.HotelFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelFacade hotelFacade;

    @Test
    public void getHotels() throws Exception {
        //Given
        List<HotelDto> list = new ArrayList<>();
        HotelDto hotelDto = new HotelDto("1", "100", "100", 5, "name", "phone",
                "address", "city", "country", "display", "url", "1");
        list.add(hotelDto);
        when(hotelFacade.getHotels(1, "Berlin", "", "", 1)).thenReturn(list);
        //When & then
        mockMvc.perform(get("/v1/hotels").contentType(MediaType.APPLICATION_JSON)
                .param("rooms", "1")
                .param("location", "Berlin")
                .param("checkin", "")
                .param("checkout", "")
                .param("adults", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].stars", is(5)));
    }

    @Test
    public void getFilteredHotels() throws Exception{
        //Given
        List<HotelDto> list = new ArrayList<>();
        HotelDto hotelDto = new HotelDto("1", "100", "100", 5, "name", "phone",
                "address", "city", "country", "display", "url", "1");
        list.add(hotelDto);
        when(hotelFacade.getFilteredHotels("1", 1D, 5, 0, 500)).thenReturn(list);
        //When & then
        mockMvc.perform(get("/v1/hotels/filter/1/").contentType(MediaType.APPLICATION_JSON)
                .param("rating", "1")
                .param("stars", "5")
                .param("priceMore", "0")
                .param("priceLess", "500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].stars", is(5)));
    }

    @Test
    public void getHotelSearchHistory() throws Exception{
        //Given
        List<HotelDto> list = new ArrayList<>();
        HotelDto hotelDto = new HotelDto("1", "100", "100", 5, "name", "phone",
                "address", "city", "country", "display", "url", "1");
        list.add(hotelDto);
        when(hotelFacade.getHotelSearchHistory()).thenReturn(list);
        //When & then
        mockMvc.perform(get("/v1/hotels/history").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].stars", is(5)));
    }

    @Test
    public void getLocations() throws Exception {
        //Given
        when(hotelFacade.getLocations("Berlin")).thenReturn(1);
        //When & then
        mockMvc.perform(get("/v1/hotels/locations/Berlin").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    public void getMostSearchedLocation() throws Exception {
        //Given
        HotelLiteDto hotelLiteDto = new HotelLiteDto("Berlin");
        when(hotelFacade.getMostSearchedLocation()).thenReturn(hotelLiteDto);
        //When & then
        mockMvc.perform(get("/v1/hotels/location/mostInterested").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.destinationLocation", is("Berlin")));
    }

    @Test
    public void saveHotelFilters() throws Exception {
        //Given
        HotelFiltersDto filtersDto = new HotelFiltersDto(50, 5, new BigDecimal(0), new BigDecimal(500));
        Gson gson = new Gson();
        String json = gson.toJson(filtersDto);
        //When & then
        mockMvc.perform(post("/v1/hotels/filter").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }
}