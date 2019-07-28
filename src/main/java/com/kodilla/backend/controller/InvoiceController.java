package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.flight.FlightInvoiceDto;
import com.kodilla.backend.domain.dto.hotel.HotelInvoiceDto;
import com.kodilla.backend.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @PostMapping("/hotels")
    public void saveHotelInvoice(@RequestBody HotelInvoiceDto hotelInvoiceDto){
        service.saveHotelInvoice(hotelInvoiceDto);
    }

    @PostMapping("/flights")
    public void saveFlightInvoice(@RequestBody FlightInvoiceDto flightInvoiceDto){
        service.saveFlightInvoice(flightInvoiceDto);
    }
}
