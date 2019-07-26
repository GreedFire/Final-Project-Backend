package com.kodilla.backend.domain.dto;

import com.kodilla.backend.domain.dto.hotel.HotelListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelInvoiceDto {
    private long id;
    private LocalDate date;
    private BigDecimal price;
    private HotelListDto hotel;
    private long userId;
}
