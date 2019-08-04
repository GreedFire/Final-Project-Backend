package com.kodilla.backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.backend.LocalDateAdapter;
import com.kodilla.backend.domain.dto.flight.FlightInvoiceDto;
import com.kodilla.backend.domain.dto.hotel.HotelInvoiceDto;
import com.kodilla.backend.service.InvoiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService service;

    @Test
    public void saveHotelInvoice() throws Exception {
        //Given
        LocalDate date = LocalDate.now();
        HotelInvoiceDto hotelInvoiceDto = new HotelInvoiceDto(1, date,new BigDecimal(100), 1, 1);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe()).create();
        String json = gson.toJson(hotelInvoiceDto);
        //When & then
        mockMvc.perform(post("/v1/invoices/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void saveFlightInvoice()  throws Exception {
        //Given
        LocalDate date = LocalDate.now();
        FlightInvoiceDto flightInvoiceDto = new FlightInvoiceDto(1, date,  new BigDecimal(100), 1, 1);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe()).create();
        String json = gson.toJson(flightInvoiceDto);
        //When & then
        mockMvc.perform(post("/v1/invoices/flights")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }
}