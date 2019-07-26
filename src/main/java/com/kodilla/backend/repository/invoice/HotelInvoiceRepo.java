package com.kodilla.backend.repository.invoice;

import com.kodilla.backend.domain.entity.HotelInvoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface HotelInvoiceRepo extends CrudRepository<HotelInvoice, Long> {

    HotelInvoice save(HotelInvoice hotelInvoice);
}
