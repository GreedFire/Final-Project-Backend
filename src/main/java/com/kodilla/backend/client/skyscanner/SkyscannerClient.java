package com.kodilla.backend.client.skyscanner;

import com.kodilla.backend.domain.dto.flight.location.FlightLocationResponseDto;
import com.kodilla.backend.domain.dto.flight.skyscanner.SkyscannerFlightReponseDto;
import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import com.kodilla.backend.domain.entity.flight.location.FlightLocationEntity;
import com.kodilla.backend.mapper.FlightMapper;
import com.kodilla.backend.service.database.FlightDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
public class SkyscannerClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkyscannerClient.class);

    @Autowired
    private SkyscannerConfig skyscannerConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FlightMapper mapper;

    @Autowired
    private FlightDatabase database;

    private URI prepareUrlForFlights(String originplace, String destinationplace, String outboundpartialdate) {
        LOGGER.info("Preparing url for skyscanner search");
        return UriComponentsBuilder.fromHttpUrl(skyscannerConfig.getSkyscannerApiEndpoint() + "browsequotes/v1.0/US/USD/en-US/"
                + originplace + "/" + destinationplace + "/" + outboundpartialdate)
                .build().encode().toUri();

    }

    private URI prepareUrlForFlightsLocation(String location) {
        LOGGER.info("Preparing url for skyscanner locations");
        return UriComponentsBuilder.fromHttpUrl(skyscannerConfig.getSkyscannerApiEndpoint() + "autosuggest/v1.0/US/USD/en-US/")
                .queryParam("query", location)
                .build().encode().toUri();
    }

    private HttpEntity<String> prepareHeaders() {
        //Set the headers you need send
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Host", skyscannerConfig.getSkyscannerHeaderHost());
        headers.set("X-RapidAPI-Key", skyscannerConfig.getSkyscannerHeaderKey());
        //Create a new HttpEntity
        return new HttpEntity<>(headers);
    }

    //==================================================================================================================

    public long getFlights(String originPlace, String destinationPlace, String outboundPartialDate) {
        long result = -1;
        try {
            String originLocation = getFlightLocationCode(originPlace);
            String destinationLocation = getFlightLocationCode(destinationPlace);

            LOGGER.info("Getting information about skyscanner from Skyscanner API");
            ResponseEntity<SkyscannerFlightReponseDto> response = restTemplate.exchange(
                    prepareUrlForFlights(originLocation, destinationLocation, outboundPartialDate),
                    HttpMethod.GET, prepareHeaders(), SkyscannerFlightReponseDto.class);

            if (response.getBody() != null) {
                FlightReponseEntity entity = mapper.mapToResponseEntity(response.getBody());
                LOGGER.info("Saving flights to database");
                if (database.getFlightsByDepartureDateAndOriginAndDestination(entity.getDepartureDate(), entity.getOrigin(), entity.getDestination()).size() == 0) {
                    database.saveFlightResponse(entity);
                    result = entity.getId();
                } else
                    result = database.getFlightsByDepartureDateAndOriginAndDestination(entity.getDepartureDate(), entity.getOrigin(), entity.getDestination()).get(0).getId();
            }
            return result;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return -1;
        }
    }

    public String getFlightLocationCode(String location) {
        String locationCode = "";
        try {
            LOGGER.info("Trying to find flight location from database");
            if (database.getFlightLocationByWritedLocation(location).size() > 0) {
                //For now always getting 0
                locationCode = database.getFlightLocationByWritedLocation(location).get(0).getPlaceId();
            } else {
                LOGGER.info("Getting skyscanner locations from SkyScanner API");
                ResponseEntity<FlightLocationResponseDto> response = restTemplate.exchange(
                        prepareUrlForFlightsLocation(location),
                        HttpMethod.GET, prepareHeaders(), FlightLocationResponseDto.class);

                if (response.getBody() != null) {
                    LOGGER.info("Saving skyscanner locations from SkyScanner API to database");
                    List<FlightLocationEntity> locationEntities = mapper.mapToLocationEntityList(response.getBody().getPlaces(), location);
                    for (FlightLocationEntity entity : locationEntities) {
                        database.saveFlightLocations(entity);
                    }
                    locationCode = response.getBody().getPlaces().get(0).getPlaceId();
                }
            }
            return locationCode;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return "";
        }
    }


}
