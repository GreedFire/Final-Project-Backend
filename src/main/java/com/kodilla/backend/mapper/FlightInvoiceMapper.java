package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.flight.FlightInvoiceDto;
import com.kodilla.backend.domain.entity.flight.FlightInvoice;
import org.springframework.stereotype.Component;

@Component
public class FlightInvoiceMapper {
    public FlightInvoice mapToEntity(FlightInvoiceDto dto){
        return new FlightInvoice(
                dto.getDate(),
                dto.getPrice(),
                dto.getCarrierId(),
                dto.getUserId()
        );
    }

    public FlightInvoiceDto mapToDto(FlightInvoice entity){
        return new FlightInvoiceDto(
                entity.getDate(),
                entity.getPrice(),
                entity.getCarrierId(),
                entity.getUserId()
        );
    }
}
