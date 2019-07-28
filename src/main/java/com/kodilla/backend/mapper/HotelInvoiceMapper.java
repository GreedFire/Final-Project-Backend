package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.hotel.HotelInvoiceDto;
import com.kodilla.backend.domain.entity.hotel.HotelInvoice;
import org.springframework.stereotype.Component;

@Component
public class HotelInvoiceMapper {
    public HotelInvoice mapToEntity(HotelInvoiceDto dto){
        return new HotelInvoice(
                dto.getDate(),
                dto.getPrice(),
                dto.getHotelId(),
                dto.getUserId()
        );
    }

    public HotelInvoiceDto mapToDto(HotelInvoice entity){
        return new HotelInvoiceDto(
                entity.getId(),
                entity.getDate(),
                entity.getPrice(),
                entity.getHotelId(),
                entity.getUserId()
        );
    }
}
