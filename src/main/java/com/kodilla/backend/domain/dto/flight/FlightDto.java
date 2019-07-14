package com.kodilla.backend.domain.dto.flight;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.backend.domain.entity.flight.flights.FlightCarriersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDto {

    private long id;

    private String departureDate;

    private String origin;

    private String destination;

    private List<FlightCarriersDto> carriers;

    public FlightDto(String departureDate, String origin, String destination) {
        this.departureDate = departureDate;
        this.origin = origin;
        this.destination = destination;
    }

    public FlightDto(long id, String departureDate, String origin, String destination) {
        this.id = id;
        this.departureDate = departureDate;
        this.origin = origin;
        this.destination = destination;
    }
}
