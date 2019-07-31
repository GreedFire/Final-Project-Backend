package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.hotel.*;
import com.kodilla.backend.domain.entity.hotel.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelMapperTestSuite {
    @Autowired
    private HotelMapper mapper;

    @Test
    public void mapToHotelEntityTest() {
        //Given
        List<HotelDto> hotels = new ArrayList<>();
        hotels.add(new HotelDto("1", "100", "1000", 5, "Hotel", "666666666",
                "adress", "city", "country", "displayAdress", "thumburl",
                "1"));
        HotelResponseDto hotelResponseDto = new HotelResponseDto("1234", "USD",
                "Berlin", "url", hotels);
        //When
        HotelResponseEntity hotelResponseEntity = mapper.mapToHotelResponseEntity(hotelResponseDto);
        //Then
        Assert.assertEquals(hotelResponseDto.getCurrency(), hotelResponseEntity.getCurrency());
        Assert.assertEquals(hotelResponseDto.getHotels().get(0).getCity(),
                hotelResponseEntity.getHotels().get(0).getCity());
        Assert.assertEquals(hotelResponseDto.getHotels().size(), hotelResponseEntity.getHotels().size());
    }

    @Test
    public void mapToHotelListDtoTest(){
        //Given
        HotelEntity entity1 = new HotelEntity("1", 100D, new BigDecimal("1000"), 5, "Hotel", "666666666",
                "adress", "city", "country", "displayAdress", "thumburl",
                new HotelResponseEntity());
        HotelEntity entity2 = new HotelEntity("2", 200D, new BigDecimal("2000"), 1, "Hotel2", "666666667",
                "adress2", "city2", "country2", "displayAdress2", "thumburl2",
                new HotelResponseEntity());
        List<HotelEntity> entityList = new ArrayList<>();
        entityList.add(entity1);
        entityList.add(entity2);
        //When
        List<HotelDto> dtoList = mapper.mapToHotelListDto(entityList);
        //Then
        Assert.assertEquals(entityList.size(), dtoList.size());
        Assert.assertEquals(entityList.get(0).getCity(), dtoList.get(0).getCity());
        Assert.assertEquals(entityList.get(1).getCountry(), dtoList.get(1).getCountry());
    }

    @Test
    public void mapToHotelLocationsDtoTest(){
        //Given
        HotelLocationEntity location = new HotelLocationEntity(1, "Berlin");
        //When
        HotelLocationDto locationDto = mapper.mapToHotelLocationsDto(location);
        //Then
        Assert.assertEquals(location.getCityId(), locationDto.getCityId());
    }

    @Test
    public void  mapToHotelLocationEntityListTest(){
        //Given
        List<HotelLocationDto> list = new ArrayList<>();
        list.add(new HotelLocationDto(1));
        list.add(new HotelLocationDto(2));
        //When
        List<HotelLocationEntity> result = mapper.mapToHotelLocationEntityList(list, "Berlin");
        //Then
        Assert.assertEquals(list.size(), result.size());
        Assert.assertEquals(list.get(0).getCityId(), result.get(0).getCityId());
        Assert.assertEquals(list.get(1).getCityId(), result.get(1).getCityId());
    }

    @Test
    public void  mapToHotelLiteDtoTest(){
        //Given
        HotelEntityLite entity = new HotelEntityLite("Berlin");
        //When
        HotelLiteDto result = mapper.mapToHotelLiteDto(entity);
        //Then
        Assert.assertEquals(entity.getDestinationLocation(), result.getDestinationLocation());
    }

    @Test
    public void  mapToHotelFiltersEntityTest() {
        //Given
        HotelFiltersDto filters = new HotelFiltersDto(100, 5, new BigDecimal(100), new BigDecimal(200));
        //When
        HotelFilters result = mapper.mapToHotelFiltersEntity(filters);
        //Then
        Assert.assertEquals(filters.getRating(), result.getRating());
        Assert.assertEquals(filters.getPriceLessThan(), result.getPriceLessThan());
        Assert.assertEquals(filters.getPriceMoreThan(), result.getPriceMoreThan());
    }


}
