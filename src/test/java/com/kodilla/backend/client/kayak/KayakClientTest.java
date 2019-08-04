package com.kodilla.backend.client.kayak;

import com.kodilla.backend.domain.dto.hotel.HotelLocationDto;
import com.kodilla.backend.domain.dto.hotel.HotelResponseDto;
import com.kodilla.backend.domain.entity.hotel.HotelLocationEntity;
import com.kodilla.backend.domain.entity.hotel.HotelResponseEntity;
import com.kodilla.backend.mapper.HotelMapper;
import com.kodilla.backend.service.database.HotelDatabase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KayakClientTest {
    @Autowired
    private KayakClient client;

    @Autowired
    private KayakConfig kayakConfig;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private HotelMapper mapper;

    @MockBean
    private HotelDatabase database;

    @Test
    public void prepareUrlForHotels() {
        //Given
        //When
        URI url = client.prepareUrlForHotels(1, 1, "2019-10-11", "2019-10.15", 1);
        //Then
        Assert.assertEquals("https://apidojo-kayak-v1.p.rapidapi.com/hotels/create-session" +
                "?rooms=1&citycode=1&checkin=2019-10-11&checkout=2019-10.15&adults=1&airportcode=none", url.toString());
    }

    @Test
    public void prepareUrlForHotelsLocation() {
        //Given
        //When
        URI url = client.prepareUrlForHotelsLocation("Berlin");
        //Then
        Assert.assertEquals("https://apidojo-kayak-v1.p.rapidapi.com/locations/search" +
                "?where=Berlin", url.toString());
    }

    @Test
    public void prepareHeaders() {
        //Given
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Host", kayakConfig.getKayakHeaderHost());
        headers.set("X-RapidAPI-Key", kayakConfig.getKayakHeaderKey());
        //Create a new HttpEntity
        HttpEntity httpEntity = new HttpEntity<>(headers);
        //when
        HttpEntity result = client.prepareHeaders();
        //Then
        Assert.assertEquals(httpEntity, result);
    }



    @Test
    public void getHotelLocationId() {
        //Given
        String location = "Berlin";
        List<HotelLocationEntity> hotelLocationEntities = new ArrayList<>();
        HotelLocationEntity entity = new HotelLocationEntity(1, "Berlin");
        hotelLocationEntities.add(entity);
        when(database.getHotelLocationByLocationName(location)).thenReturn(hotelLocationEntities);

        HotelLocationDto dto = new HotelLocationDto(1);
        when(mapper.mapToHotelLocationsDto(entity)).thenReturn(dto);

        List<HotelLocationDto> listDto = new ArrayList<>();
        HotelLocationDto clientAnswer = new HotelLocationDto(1);
        listDto.add(clientAnswer);
        ResponseEntity<List<HotelLocationDto>> responseEntity = new ResponseEntity<>(listDto, HttpStatus.OK);
        //HEADERS
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Host", kayakConfig.getKayakHeaderHost());
        headers.set("X-RapidAPI-Key", kayakConfig.getKayakHeaderKey());
        HttpEntity httpEntity = new HttpEntity<>(headers);
        URI url = UriComponentsBuilder.fromHttpUrl(kayakConfig.getKayakApiEndpoint() + "locations/search")
                .queryParam("where", "Berlin")
                .build().encode().toUri();
        when(restTemplate.exchange(url, HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<List<HotelLocationDto>>() {
                }))
                .thenReturn(responseEntity);

        List<HotelLocationEntity> listEntity = new ArrayList<>();
        listEntity.add(entity);
        when(mapper.mapToHotelLocationEntityList(listDto, "Berlin")).thenReturn(listEntity);

        //When
        int result = client.getHotelLocationId("Berlin");
        //Then
        Assert.assertEquals(1, result);
    }
}