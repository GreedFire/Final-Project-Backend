package com.kodilla.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HOTEL_FILTERS")
public class HotelFilters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int rating;

    private int stars;

    private BigDecimal priceMoreThan;

    private BigDecimal priceLessThan;

    public HotelFilters(int rating, int stars, BigDecimal priceMoreThan, BigDecimal priceLessThan) {
        this.rating = rating;
        this.stars = stars;
        this.priceMoreThan = priceMoreThan;
        this.priceLessThan = priceLessThan;
    }
}
