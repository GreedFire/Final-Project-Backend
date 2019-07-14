package com.kodilla.backend.domain.dto.flight.skyscanner.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkyscannerFlightPlacesDto {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("CountryName")
    private String country;
}
