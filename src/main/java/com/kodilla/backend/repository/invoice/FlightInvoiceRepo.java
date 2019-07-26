package com.kodilla.backend.repository.invoice;

import com.kodilla.backend.domain.entity.FlightInvoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FlightInvoiceRepo extends CrudRepository<FlightInvoice, Long> {

    FlightInvoice save(FlightInvoice flightInvoice);
}
