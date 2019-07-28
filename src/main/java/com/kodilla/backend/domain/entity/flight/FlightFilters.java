package com.kodilla.backend.domain.entity.flight;

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
@Table(name = "FLIGHT_FILTERS")
public class FlightFilters {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String carrierClass;

    private BigDecimal priceMoreThan;

    private BigDecimal priceLessThan;

    public FlightFilters(String carrierClass, BigDecimal priceMoreThan, BigDecimal priceLessThan) {
        this.carrierClass = carrierClass;
        this.priceMoreThan = priceMoreThan;
        this.priceLessThan = priceLessThan;
    }
}
