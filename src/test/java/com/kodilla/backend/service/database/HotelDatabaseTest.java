package com.kodilla.backend.service.database;

import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import com.kodilla.backend.domain.entity.hotel.HotelLocationEntity;
import com.kodilla.backend.domain.entity.hotel.HotelResponseEntity;
import com.kodilla.backend.repository.hotel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class HotelDatabaseTest {

    @Autowired
    private HotelDatabase database;

    @MockBean
    private HoteResponselRepo hoteResponselRepo;

    @MockBean
    private HotelLocationRepo hotelLocationRepo;

    @MockBean
    private HotelRepo hotelRepo;

    @MockBean
    private HotelEntityLiteRepo hotelEntityLiteRepo;

    @MockBean
    private HotelFiltersRepo hotelFiltersRepo;

    @Test
    public void saveHotel() {
        //Given
        HotelResponseEntity entity = new HotelResponseEntity("1", "USD", "loc", "url");
        when(hoteResponselRepo.save(entity)).thenReturn(entity);
        //When
        HotelResponseEntity result = database.saveHotel(entity);
        //Then
        assertEquals(entity.getCurrency(), result.getCurrency());
        Mockito.verify(hoteResponselRepo, atLeastOnce()).save(entity);
    }

    @Test
    public void getSearchHistory() {
        //Given
        LocalDate date = LocalDate.now().minusDays(1);
        List<HotelEntity> list = new ArrayList<>();
        HotelEntity entity = new HotelEntity();
        list.add(entity);
        when(hotelRepo.findAllBySearchDateAfter(date)).thenReturn(list);
        //When
        List<HotelEntity> result = database.getSearchHistory();
        //Then
        assertEquals(list.size(), result.size());
        Mockito.verify(hotelRepo, atLeastOnce()).findAllBySearchDateAfter(date);
    }

    @Test
    public void getHotelLocationByCityId() {
    }

    @Test
    public void getHotelLocationByLocationName() {
    }

    @Test
    public void getFilteredHotels() {
    }

    @Test
    public void getHotelsBySearchId() {
    }

    @Test
    public void getMostSearchedLocation() {
    }

    @Test
    public void saveMostSearchedLocation() {
    }

    @Test
    public void saveHotelFilters() {
    }
}