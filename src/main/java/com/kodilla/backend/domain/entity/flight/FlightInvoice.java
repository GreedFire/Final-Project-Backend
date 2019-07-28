package com.kodilla.backend.domain.entity.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FLIGHT_INVOICE")
public class FlightInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate date;

    private BigDecimal price;

    private long carrierId;

    private long userId;

    public FlightInvoice(LocalDate date, BigDecimal price, long carrierId, long userId) {
        this.date = date;
        this.price = price;
        this.carrierId = carrierId;
        this.userId = userId;
    }
}
