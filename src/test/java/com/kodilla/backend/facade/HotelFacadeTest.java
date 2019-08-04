package com.kodilla.backend.facade;

import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelFiltersDto;
import com.kodilla.backend.domain.dto.hotel.HotelLiteDto;
import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import com.kodilla.backend.domain.entity.hotel.HotelEntityLite;
import com.kodilla.backend.domain.entity.hotel.HotelFilters;
import com.kodilla.backend.mapper.HotelMapper;
import com.kodilla.backend.service.HotelService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelFacadeTest {

    @Autowired
    private HotelFacade facade;

    @MockBean
    private HotelMapper mapper;

    @MockBean
    private HotelService service;

    @Test
    public void getHotels() {
        //Given
        List<HotelEntity> list = new ArrayList<>();
        when(service.getHotels(1, "Berlin", "", "", 1)).thenReturn(list);
        when(mapper.mapToHotelListDto(list)).thenReturn(new ArrayList<>());
        //Then
        List<HotelDto> result =  facade.getHotels(1, "Berlin", "", "", 1);
        //When
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void getFilteredHotels() {
        //Given
        List<HotelEntity> list = new ArrayList<>();
        when(service.getFilteredHotels("1",100D, 5, 100, 1000 )).thenReturn(list);
        when(mapper.mapToHotelListDto(list)).thenReturn(new ArrayList<>());
        //Then
        List<HotelDto> result =  facade.getFilteredHotels("1",100D, 5, 100, 1000 );
        //When
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void getHotelSearchHistory() {
        //Given
        List<HotelEntity> list = new ArrayList<>();
        when(service.getHotelSearchHistory()).thenReturn(list);
        when(mapper.mapToHotelListDto(list)).thenReturn(new ArrayList<>());
        //Then
        List<HotelDto> result =  facade.getHotelSearchHistory();
        //When
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void getLocations() {
        //Given
        when(service.getLocations("Berlin")).thenReturn(1);
        //Then
        int result = facade.getLocations("Berlin");
        //When
        Assert.assertEquals(1, result);
    }

    @Test
    public void getMostSearchedLocation() {
        //Given
        HotelEntityLite entity = new HotelEntityLite("Berlin");
        HotelLiteDto dto = new HotelLiteDto("Berlin");
        when(service.getMostSearchedLocation()).thenReturn(entity);
        when(mapper.mapToHotelLiteDto(entity)).thenReturn(dto);
        //Then
        HotelLiteDto result = facade.getMostSearchedLocation();
        //When
        Assert.assertEquals("Berlin" ,result.getDestinationLocation());
    }


}