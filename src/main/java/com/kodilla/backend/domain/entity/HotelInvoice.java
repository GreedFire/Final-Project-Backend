package com.kodilla.backend.domain.entity;

import com.kodilla.backend.domain.entity.hotel.HotelListEntity;
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
@Table(name = "HOTEL_INVOICE")
public class HotelInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate date;

    private BigDecimal price;

    private long hotelId;

    private long userId;

    public HotelInvoice(LocalDate date, BigDecimal price, long hotelId, long userId) {
        this.date = date;
        this.price = price;
        this.hotelId = hotelId;
        this.userId = userId;
    }
}
