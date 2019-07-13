package com.kodilla.backend.domain.dto.flight.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightLocationDto {

    @JsonProperty("PlaceId")
    private String placeId;

    @JsonProperty("PlaceName")
    private String placeName;

    @JsonProperty("CountryName")
    private String countryName;
}
