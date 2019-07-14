package com.kodilla.backend.domain.dto.flight;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.backend.domain.entity.flight.flights.FlightReponseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightCarriersDto {

    private long id;

    private int carrierId;

    private String carrierName;

    private BigDecimal price;

    private String outboundDate;


}
