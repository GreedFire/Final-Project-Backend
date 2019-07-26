package com.kodilla.backend.service.database;

import com.kodilla.backend.domain.entity.FlightInvoice;
import com.kodilla.backend.domain.entity.HotelInvoice;
import com.kodilla.backend.repository.invoice.FlightInvoiceRepo;
import com.kodilla.backend.repository.invoice.HotelInvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDatabase {

    @Autowired
    private HotelInvoiceRepo hotelInvoiceRepo;

    @Autowired
    private FlightInvoiceRepo flightInvoiceRepo;

    public void saveHotelInvoice(HotelInvoice hotelInvoice) {
        hotelInvoiceRepo.save(hotelInvoice);
    }

    public void saveFlightInvoice(FlightInvoice flightInvoice) {
        flightInvoiceRepo.save(flightInvoice);
    }


}
