package com.kodilla.backend.service;

import com.kodilla.backend.domain.dto.flight.FlightInvoiceDto;
import com.kodilla.backend.domain.dto.hotel.HotelInvoiceDto;
import com.kodilla.backend.mapper.FlightInvoiceMapper;
import com.kodilla.backend.mapper.HotelInvoiceMapper;
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
