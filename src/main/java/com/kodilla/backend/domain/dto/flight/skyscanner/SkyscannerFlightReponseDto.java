package com.kodilla.backend.domain.dto.flight.skyscanner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.backend.domain.dto.flight.skyscanner.lists.SkyscannerFlightCarriersDto;
import com.kodilla.backend.domain.dto.flight.skyscanner.lists.SkyscannerFlightPlacesDto;
import com.kodilla.backend.domain.dto.flight.skyscanner.lists.SkyscannerFlightQuotesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkyscannerFlightReponseDto {
    @JsonProperty("Quotes")
    private List<SkyscannerFlightQuotesDto> quotes;

    @JsonProperty("Places")
    private List<SkyscannerFlightPlacesDto> places;

    @JsonProperty("Carriers")
    private List<SkyscannerFlightCarriersDto> carriers;
}
