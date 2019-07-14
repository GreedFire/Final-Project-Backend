package com.kodilla.backend.domain.entity.flight.flights;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "FLIGHTS_CARRIERS")
public class FlightCarriersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int carriedId;

    private String carrierName;

    private BigDecimal price;

    private LocalDateTime outboundDate;

    @ManyToOne
    @JoinColumn(name = "Flight_Response_ID")
    private FlightReponseEntity flightReponseEntity;

    public FlightCarriersEntity(int carriedId, String carrierName, BigDecimal price, LocalDateTime outboundDate, FlightReponseEntity flightReponseEntity) {
        this.carriedId = carriedId;
        this.carrierName = carrierName;
        this.price = price;
        this.outboundDate = outboundDate;
        this.flightReponseEntity = flightReponseEntity;
    }
}

