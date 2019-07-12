package com.kodilla.backend.domain.dto.flight.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightLocationResponseDto {

    @JsonProperty("Places")
    FlightLocationDto[] places;

    @Override
    public String toString() {
        return "FlightLocationResponseDto{" +
                "places=" + Arrays.toString(places) +
                '}';
    }
}
