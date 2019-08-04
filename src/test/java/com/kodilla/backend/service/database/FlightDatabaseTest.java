package com.kodilla.backend.service.database;

import com.kodilla.backend.domain.entity.flight.FlightCarriersEntity;
import com.kodilla.backend.domain.entity.flight.FlightFilters;
import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import com.kodilla.backend.domain.entity.flight.location.FlightLocationEntity;
import com.kodilla.backend.repository.FlightCarriersRepo;
import com.kodilla.backend.repository.FlightFiltersRepo;
import com.kodilla.backend.repository.FlightLocationRepo;
import com.kodilla.backend.repository.FlightResponseRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightDatabaseTest {

    @Autowired
    private FlightDatabase database;

    @MockBean
    private FlightResponseRepo flightResponseRepo;

    @MockBean
    private FlightLocationRepo flightLocationRepo;

    @MockBean
    private FlightFiltersRepo flightFiltersRepo;

    @MockBean
    private FlightCarriersRepo flightCarriersRepo;

    @Test
    public void saveFlightResponse() {
        //Given
        LocalDateTime dateTime = LocalDateTime.now();
        FlightReponseEntity entity = new FlightReponseEntity(dateTime, "Berlin", "New York");
        when(flightResponseRepo.save(entity)).thenReturn(entity);
        //When
        FlightReponseEntity result = database.saveFlightResponse(entity);
        //Then
        assertEquals("Berlin", result.getOrigin());
    }

    @Test
    public void saveFlightLocations() {
        //Given
        FlightLocationEntity entity = new FlightLocationEntity();
        Optional<FlightLocationEntity> optionalFlightLocationEntity = Optional.of(entity);
        when(flightLocationRepo.findByPlaceId("1")).thenReturn(optionalFlightLocationEntity);
        when(flightLocationRepo.save(entity)).thenReturn(entity);
        //When
        database.saveFlightLocations(entity);
        //Then
        Mockito.verify(flightLocationRepo, atLeastOnce()).findByPlaceId(null);
        Mockito.verify(flightLocationRepo, atLeastOnce()).save(entity);
    }

    @Test
    public void saveFlightFilters(){
        //Given
        FlightFilters entity = new FlightFilters();
        when(flightFiltersRepo.save(entity)).thenReturn(entity);
        //When
        database.saveFlightFilters(entity);
        //Then
        Mockito.verify(flightFiltersRepo, atLeastOnce()).save(entity);
    }

    @Test
    public void getFlightLocationByWritedLocation(){
        //Given
        List<FlightLocationEntity> list = new ArrayList<>();
        FlightLocationEntity entity = new FlightLocationEntity();
        list.add(entity);
        when(flightLocationRepo.findByWritedLocation("Berlin")).thenReturn(list);
        //When
        List<FlightLocationEntity> result = database.getFlightLocationByWritedLocation("Berlin");
        //Then
        Mockito.verify(flightLocationRepo, atLeastOnce()).findByWritedLocation("Berlin");
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void getFlightsByDepartureDateAndOriginAndDestination(){
        //Given
        LocalDateTime dateTime = LocalDateTime.now();
        List<FlightReponseEntity> list = new ArrayList<>();
        FlightReponseEntity entity = new FlightReponseEntity();
        list.add(entity);
        when(flightResponseRepo.findByDepartureDateAndOriginAndDestination(dateTime, "origin", "destination")).thenReturn(list);
        //When
        List<FlightReponseEntity> result = database.getFlightsByDepartureDateAndOriginAndDestination(dateTime, "origin", "destination");
        //Then
        Mockito.verify(flightResponseRepo, atLeastOnce()).findByDepartureDateAndOriginAndDestination(dateTime, "origin", "destination");
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void getSearchHistory(){
        //Given
        LocalDate date = LocalDate.now().minusDays(1);
        List<FlightReponseEntity> list = new ArrayList<>();
        FlightReponseEntity entity = new FlightReponseEntity();
        list.add(entity);
        when(flightResponseRepo.findAllBySearchDateAfter(date)).thenReturn(list);
        //When
        List<FlightReponseEntity> result = database.getSearchHistory();
        //Then
        Mockito.verify(flightResponseRepo, atLeastOnce()).findAllBySearchDateAfter(date);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void getFlightResponseById(){
        //Given
        FlightReponseEntity entity = new FlightReponseEntity();
        Optional<FlightReponseEntity> optional = Optional.of(entity);
        when(flightResponseRepo.findById(1)).thenReturn(optional);
        //When
        Optional<FlightReponseEntity> result = database.getFlightResponseById(1);
        //Then
        Mockito.verify(flightResponseRepo, atLeastOnce()).findById(1);
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void getFilteredFlights(){
        //Given
        FlightCarriersEntity entity = new FlightCarriersEntity();
        List<FlightCarriersEntity> list = new ArrayList<>();
        list.add(entity);
        when(flightCarriersRepo.retrieveFilteredFlights(1, "class", 0, 100)).thenReturn(list);
        //When
        List<FlightCarriersEntity> result = database.getFilteredFlights(1, "class", 0, 100);
        //Then
        Mockito.verify(flightCarriersRepo, atLeastOnce()).retrieveFilteredFlights(1, "class", 0, 100);
        Assert.assertFalse(result.isEmpty());
    }

}