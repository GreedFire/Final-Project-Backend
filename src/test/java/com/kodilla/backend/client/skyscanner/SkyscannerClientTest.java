package com.kodilla.backend.client.skyscanner;

import com.kodilla.backend.mapper.FlightMapper;
import com.kodilla.backend.service.database.FlightDatabase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkyscannerClientTest {

    @Autowired
    private SkyscannerClient client;

    @Autowired
    private SkyscannerConfig skyscannerConfig;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private FlightMapper mapper;

    @MockBean
    private FlightDatabase database;

    @Test
    public void prepareUrlForFlights() {
        //Given
        //When
        URI url = client.prepareUrlForFlights("Berlin", "Warszawa", "");
        //Then
        Assert.assertEquals("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/" +
                "browsequotes/v1.0/US/USD/en-US/Berlin/Warszawa/", url.toString());
    }

    @Test
    public void prepareUrlForFlightsLocation() {
        //Given
        //When
        URI url = client.prepareUrlForFlightsLocation("Berlin");
        //Then
        Assert.assertEquals("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/" +
                "autosuggest/v1.0/US/USD/en-US/?query=Berlin", url.toString());
    }

    @Test
    public void prepareHeaders() {
        //Given
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Host", skyscannerConfig.getSkyscannerHeaderHost());
        headers.set("X-RapidAPI-Key", skyscannerConfig.getSkyscannerHeaderKey());
        HttpEntity httpEntity = new HttpEntity<>(headers);
        //When
        HttpEntity result = client.prepareHeaders();
        //Then
        Assert.assertEquals(httpEntity, result);
    }

    @Test
    public void getFlights() {
        //Given
        //When
        //Then
    }

    @Test
    public void getFlightLocationCode() {
        //Given
        //When
        //Then
    }
}