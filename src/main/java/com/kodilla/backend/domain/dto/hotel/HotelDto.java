package com.kodilla.backend.domain.dto.hotel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelDto {

    @JsonProperty("searchid")
    private String searchId;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("destinationLocation")
    private String destinationLocation;

    @JsonProperty("shareURL")
    private String shareURL;

    @JsonProperty("hotelset")
    private List<HotelListDto> hotels;

    public HotelDto(String searchId, String currency, String destinationLocation, String shareURL) {
        this.searchId = searchId;
        this.currency = currency;
        this.destinationLocation = destinationLocation;
        this.shareURL = shareURL;
    }
}
