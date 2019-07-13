package com.kodilla.backend.domain.dto.flight.flights;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.backend.domain.dto.flight.flights.lists.FlightCarriersDto;
import com.kodilla.backend.domain.dto.flight.flights.lists.FlightPlacesDto;
import com.kodilla.backend.domain.dto.flight.flights.lists.FlightQuotesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightReponseDto {
    @JsonProperty("Quotes")
    private List<FlightQuotesDto> quotes;

    @JsonProperty("Places")
    private List<FlightPlacesDto> places;

    @JsonProperty("Carriers")
    private List<FlightCarriersDto> carriers;
}
