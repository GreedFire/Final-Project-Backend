package com.kodilla.backend.client.skyscanner;

import com.kodilla.backend.domain.dto.flight.flights.FlightReponseDto;
import com.kodilla.backend.domain.dto.flight.location.FlightLocationResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
public class SkyscannerClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkyscannerClient.class);

    @Autowired
    private SkyscannerConfig skyscannerConfig;

    @Autowired
    private RestTemplate restTemplate;

    private URI prepareUrlForFlights(String originplace, String destinationplace, String outboundpartialdate, String inboundpartialdate){
        if(inboundpartialdate == null)
            inboundpartialdate = "2019-12-01";

        LOGGER.info("Preparing url for flights search");
        return UriComponentsBuilder.fromHttpUrl(skyscannerConfig.getSkyscannerApiEndpoint() + "browsequotes/v1.0/US/USD/en-US/"
        + originplace + "/" + destinationplace + "/" + outboundpartialdate)
                .queryParam("inboundpartialdate", inboundpartialdate)
                .build().encode().toUri();

    }

    private URI prepareUrlForFlightsLocation(String location){
        LOGGER.info("Preparing url for flight locations");
        return UriComponentsBuilder.fromHttpUrl(skyscannerConfig.getSkyscannerApiEndpoint() + "autosuggest/v1.0/US/USD/en-US/")
                .queryParam("query", location)
                .build().encode().toUri();
    }

    private HttpEntity<String> prepareHeaders(){
        //Set the headers you need send
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Host", skyscannerConfig.getSkyscannerHeaderHost());
        headers.set("X-RapidAPI-Key",  skyscannerConfig.getSkyscannerHeaderKey());
        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        return entity;
    }

    public FlightLocationResponseDto getFlightLocationCode(String location){
        try {
            LOGGER.info("Getting flight locations");
            ResponseEntity<FlightLocationResponseDto> response = restTemplate.exchange(
                    prepareUrlForFlightsLocation(location),
                    HttpMethod.GET, prepareHeaders(), FlightLocationResponseDto.class);

            return Optional.ofNullable(response.getBody()).orElse(new FlightLocationResponseDto());
        }  catch(RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new FlightLocationResponseDto();
        }
    }

    public FlightReponseDto getFlights(String originPlace, String destinationPlace, String outboundPartialDate, String inboundPartialDate){

        try {
            String originLocation = getFlightLocationCode(originPlace).getPlaces()[0].getPlaceId();
            String destinationLocation = getFlightLocationCode(destinationPlace).getPlaces()[0].getPlaceId();
            LOGGER.info("Getting flights");
            ResponseEntity<FlightReponseDto> response = restTemplate.exchange(
                    prepareUrlForFlights(originLocation, destinationLocation, outboundPartialDate, inboundPartialDate),
                    HttpMethod.GET, prepareHeaders(), FlightReponseDto.class);

            return Optional.ofNullable(response.getBody()).orElse(new FlightReponseDto());
        }  catch(RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new FlightReponseDto();
        }
    }


}
