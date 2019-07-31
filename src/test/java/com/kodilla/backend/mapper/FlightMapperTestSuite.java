package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.flight.FlightCarriersDto;
import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.dto.flight.FlightFiltersDto;
import com.kodilla.backend.domain.dto.flight.location.FlightLocationDto;
import com.kodilla.backend.domain.dto.flight.skyscanner.SkyscannerFlightReponseDto;
import com.kodilla.backend.domain.dto.flight.skyscanner.lists.SkyscannerFlightCarriersDto;
import com.kodilla.backend.domain.dto.flight.skyscanner.lists.SkyscannerFlightPlacesDto;
import com.kodilla.backend.domain.dto.flight.skyscanner.lists.SkyscannerFlightQuotesDto;
import com.kodilla.backend.domain.dto.flight.skyscanner.lists.SkyscannerOutboundLegDto;
import com.kodilla.backend.domain.entity.flight.FlightCarriersEntity;
import com.kodilla.backend.domain.entity.flight.FlightFilters;
import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import com.kodilla.backend.domain.entity.flight.location.FlightLocationEntity;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightMapperTestSuite {
    @Autowired
    private FlightMapper mapper;

    @Test
    public void mapToFlightCarriersListDto() {
        //Given
        LocalDateTime date = LocalDateTime.now();
        FlightCarriersEntity carriersEntity = new FlightCarriersEntity(1, "carrier",
                new BigDecimal("100"), date, "class", new FlightReponseEntity());
        FlightCarriersEntity carriersEntity2 = new FlightCarriersEntity(2, "carrier2",
                new BigDecimal("200"), date, "class2", new FlightReponseEntity());
        List<FlightCarriersEntity> carriers = new ArrayList<>();
        carriers.add(carriersEntity);
        carriers.add(carriersEntity2);
        //When
        List<FlightCarriersDto> result = mapper.mapToFlightCarriersListDto(carriers);
        //Then
        Assert.assertEquals(carriers.size(), result.size());
        Assert.assertEquals(carriers.get(0).getCarrierName(), result.get(0).getCarrierName());
        Assert.assertEquals(carriers.get(1).getCarrierName(), result.get(1).getCarrierName());
        Assert.assertEquals(carriers.get(0).getOutboundDate(), result.get(0).getDepartureDate());
        Assert.assertEquals(carriers.get(1).getCarrierClass(), result.get(1).getCarrierClass());
    }

    @Test
    public void mapToFlightDtoList() {
        //Given
        LocalDateTime dateTime = LocalDateTime.now();
        FlightReponseEntity entity = new FlightReponseEntity(dateTime, "origin", "destnation");
        FlightReponseEntity entity2 = new FlightReponseEntity(dateTime, "origin2", "destnation2");
        entity.setCarriers(new ArrayList<>());
        entity2.setCarriers(new ArrayList<>());
        List<FlightReponseEntity> list = new ArrayList<>();
        list.add(entity);
        list.add(entity2);
        //When
        List<FlightDto> result = mapper.mapToFlightDtoList(list);
        //Then
        Assert.assertEquals(list.size(), result.size());
        Assert.assertEquals(list.get(0).getDepartureDate(), result.get(0).getDepartureDate());
        Assert.assertEquals(list.get(0).getOrigin(), result.get(0).getOrigin());
        Assert.assertEquals(list.get(1).getDestination(), result.get(1).getDestination());
    }

    @Test
    public void testMapToFlightDtoList() {
        //Given
        LocalDateTime dateTime = LocalDateTime.now();
        FlightReponseEntity entity = new FlightReponseEntity(dateTime, "origin", "destnation");
        //When
        List<FlightDto> result = mapper.mapToFlightDtoList(entity);
        //Then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(entity.getOrigin(), result.get(0).getOrigin());
    }

    @Test
    public void mapToFlightDto() {
        //Given
        LocalDateTime dateTime = LocalDateTime.now();
        FlightReponseEntity entity = new FlightReponseEntity(dateTime, "origin", "destnation");
        //When
        FlightDto result = mapper.mapToFlightDto(entity);
        //Then
        Assert.assertEquals(entity.getOrigin(), result.getOrigin());
        Assert.assertEquals(entity.getDestination(), result.getDestination());
    }

    @Test
    public void mapToResponseEntity() {
        //Given
        SkyscannerFlightQuotesDto quotesDto = new SkyscannerFlightQuotesDto(new BigDecimal("100"), new SkyscannerOutboundLegDto("2019-09-01T00:00:00"));
        SkyscannerFlightPlacesDto placesDto = new SkyscannerFlightPlacesDto("name", "country");
        SkyscannerFlightPlacesDto placesDto2 = new SkyscannerFlightPlacesDto("name2", "country2");
        SkyscannerFlightCarriersDto carriersDto = new SkyscannerFlightCarriersDto(1, "carrierName");
        List<SkyscannerFlightQuotesDto> quotesDtoList = new ArrayList<>();
        List<SkyscannerFlightPlacesDto> placesDtoList = new ArrayList<>();
        List<SkyscannerFlightCarriersDto> carriersDtoList = new ArrayList<>();
        quotesDtoList.add(quotesDto);
        placesDtoList.add(placesDto);
        placesDtoList.add(placesDto2);
        carriersDtoList.add(carriersDto);
        SkyscannerFlightReponseDto dto = new SkyscannerFlightReponseDto(quotesDtoList, placesDtoList, carriersDtoList);
        //When
        FlightReponseEntity result = mapper.mapToResponseEntity(dto);
        //Then
        Assert.assertEquals(dto.getCarriers().get(0).getCarrierName(), result.getCarriers().get(0).getCarrierName());
        Assert.assertEquals(dto.getPlaces().get(0).getName(), result.getDestination());
    }

    @Test
    public void mapToLocationEntityList() {
        //Given
        FlightLocationDto locationDto = new FlightLocationDto("1", "placeName", "countryName");
        FlightLocationDto locationDto2 = new FlightLocationDto("2", "placeName2", "countryName2");
        List<FlightLocationDto> list = new ArrayList<>();
        list.add(locationDto);
        list.add(locationDto2);
        //When
        List<FlightLocationEntity> result = mapper.mapToLocationEntityList(list, "Berlin");
        //Then
        Assert.assertEquals(list.size(), result.size());
        Assert.assertEquals(list.get(0).getCountryName(), result.get(0).getCountryName());
        Assert.assertEquals(list.get(1).getPlaceName(), result.get(1).getPlaceName());
    }

    @Test
    public void mapToFlightFiltersEntity() {
        //Given
        FlightFiltersDto filters = new FlightFiltersDto("carrierClass", new BigDecimal("100"), new BigDecimal(200));
        //When
        FlightFilters result = mapper.mapToFlightFiltersEntity(filters);
        //Then
        Assert.assertEquals(filters.getCarrierClass(), result.getCarrierClass());
        Assert.assertEquals(filters.getPriceLessThan(), result.getPriceLessThan());
        Assert.assertEquals(filters.getPriceMoreThan(), result.getPriceMoreThan());
    }
}