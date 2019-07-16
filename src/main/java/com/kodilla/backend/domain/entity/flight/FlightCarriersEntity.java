package com.kodilla.backend.domain.entity.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NamedNativeQuery(
        name = "FlightCarriersEntity.retrieveFilteredFlights",
        query = "SELECT * FROM FLIGHTS_CARRIERS WHERE FLIGHT_RESPONSE_ID = :RESPONSEID AND CARRIER_CLASS = :PLANECLASS AND PRICE BETWEEN :PRICEMORE AND :PRICELESS",
        resultClass = FlightCarriersEntity.class
)

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

    private String carrierClass;

    @ManyToOne
    @JoinColumn(name = "Flight_Response_ID")
    private FlightReponseEntity flightReponseEntity;

    public FlightCarriersEntity(int carriedId, String carrierName, BigDecimal price, LocalDateTime outboundDate, String carrierClass, FlightReponseEntity flightReponseEntity) {
        this.carriedId = carriedId;
        this.carrierName = carrierName;
        this.price = price;
        this.outboundDate = outboundDate;
        this.carrierClass = carrierClass;
        this.flightReponseEntity = flightReponseEntity;
    }

}

