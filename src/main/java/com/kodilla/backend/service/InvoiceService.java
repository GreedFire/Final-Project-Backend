package com.kodilla.backend.service;

import com.kodilla.backend.domain.dto.FlightInvoiceDto;
import com.kodilla.backend.domain.dto.HotelInvoiceDto;
import com.kodilla.backend.mapper.mappers.FlightInvoiceMapper;
import com.kodilla.backend.mapper.mappers.HotelInvoiceMapper;
import com.kodilla.backend.service.database.InvoiceDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceDatabase database;

    @Autowired
    private HotelInvoiceMapper hotelMapper;

    @Autowired
    private FlightInvoiceMapper flightMapper;

    public void saveHotelInvoice(HotelInvoiceDto hotelInvoiceDto){
        database.saveHotelInvoice(hotelMapper.mapToEntity(hotelInvoiceDto));
    }

    public void saveFlightInvoice(FlightInvoiceDto flightInvoiceDto){
        database.saveFlightInvoice(flightMapper.mapToEntity(flightInvoiceDto));
    }
}
