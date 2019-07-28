package com.kodilla.backend.domain.dto.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightInvoiceDto {
    private long id;
    private LocalDate date;
    private BigDecimal price;
    private long carrierId;
    private long userId;

    public FlightInvoiceDto(LocalDate date, BigDecimal price, long carrierId, long userId) {
        this.date = date;
        this.price = price;
        this.carrierId = carrierId;
        this.userId = userId;
    }
}
