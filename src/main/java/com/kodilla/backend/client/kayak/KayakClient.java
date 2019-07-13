package com.kodilla.backend.client.kayak;

import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelLocationDto;
import com.kodilla.backend.domain.entity.hotel.HotelLocationEntity;
import com.kodilla.backend.mapper.HotelMapper;
import com.kodilla.backend.service.HotelDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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
import java.util.Optional;

@Component
public class KayakClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(KayakClient.class);

    @Autowired
    private KayakConfig kayakConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelMapper mapper;

    @Autowired
    private HotelDatabase database;

    private URI prepareUrlForHotels(int rooms, int citycode, String checkin, String checkout, int adults) {
        LOGGER.info("Preparing url for hotels search");
        return UriComponentsBuilder.fromHttpUrl(kayakConfig.getKayakApiEndpoint() + "hotels/create-session")
                .queryParam("rooms", rooms)
                .queryParam("citycode", citycode)
                .queryParam("checkin", checkin)
                .queryParam("checkout", checkout)
                .queryParam("adults", adults)
                .queryParam("airportcode", "none")
                .build().encode().toUri();
    }

    private URI prepareUrlForHotelsLocation(String cityName) {
        LOGGER.info("Preparing url of locations of hotels");
        return UriComponentsBuilder.fromHttpUrl(kayakConfig.getKayakApiEndpoint() + "locations/search")
                .queryParam("where", cityName)
                .build().encode().toUri();
    }

    private HttpEntity<String> prepareHeaders() {
        //Set the headers you need send
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Host", kayakConfig.getKayakHeaderHost());
        headers.set("X-RapidAPI-Key", kayakConfig.getKayakHeaderKey());
        //Create a new HttpEntity
        return new HttpEntity<>(headers);
    }

    public HotelDto getHotels(int rooms, String location, String checkin, String checkout, int adults) {
        try {
            int citycode = getHotelLocationId(location);

            LOGGER.info("Getting hotels from Kayak API: ");
            ResponseEntity<HotelDto> response = restTemplate.exchange(
                    prepareUrlForHotels(rooms, citycode, checkin, checkout, adults),
                    HttpMethod.GET, prepareHeaders(), HotelDto.class);

            if (response.getBody() != null) {
                LOGGER.info("Saving hotels to database: ");
                database.saveHotel(mapper.mapToHotelEntity(response.getBody()));
            }

            return Optional.ofNullable(response.getBody()).orElse(new HotelDto());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new HotelDto();
        }
    }

    public int getHotelLocationId(String location) {
        int cityCode = 0;
        try {
            LOGGER.info("Trying to find hotel location from database: ");
            if (database.getHotelLocationByLocationName(location).size() > 0) {
                //For now always getting 0
                cityCode = mapper.mapToHotelLocationsDto(database.getHotelLocationByLocationName(location).get(0)).getCityId();
            } else {
                LOGGER.info("Getting location of hotels from Kayak API: ");
                ResponseEntity<List<HotelLocationDto>> response = restTemplate.exchange(
                        prepareUrlForHotelsLocation(location), HttpMethod.GET, prepareHeaders(), new ParameterizedTypeReference<List<HotelLocationDto>>() {
                        });

                if (response.getBody() != null) {
                    LOGGER.info("Saving hotels locations from Kayak API to database");
                    List<HotelLocationEntity> locationEntities = mapper.mapToHotelLocationEntityList(response.getBody(), location);
                    for (HotelLocationEntity entity : locationEntities) {
                        database.saveHotelLocations(entity);
                    }
                    cityCode = response.getBody().get(0).getCityId();
                }
            }
            return cityCode;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return 0;
        }
    }
}
