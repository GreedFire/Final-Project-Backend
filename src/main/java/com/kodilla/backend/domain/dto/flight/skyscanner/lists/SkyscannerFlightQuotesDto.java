package com.kodilla.backend.domain.dto.flight.skyscanner.lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkyscannerFlightQuotesDto {

    @JsonProperty("MinPrice")
    private BigDecimal price;

    @JsonProperty("OutboundLeg")
    private SkyscannerOutboundLegDto outBoundLeg;

}
