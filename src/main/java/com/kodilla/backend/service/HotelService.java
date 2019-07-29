package com.kodilla.backend.service;

import com.kodilla.backend.client.kayak.KayakClient;
import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelFiltersDto;
import com.kodilla.backend.domain.dto.hotel.HotelLiteDto;
import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import com.kodilla.backend.domain.entity.hotel.HotelFilters;
import com.kodilla.backend.domain.entity.hotel.HotelResponseEntity;
import com.kodilla.backend.domain.entity.hotel.HotelEntityLite;
import com.kodilla.backend.service.database.HotelDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private KayakClient kayakClient;

    @Autowired
    private HotelDatabase database;

    public List<HotelEntity> getHotels(int rooms, String location, String checkin, String checkout, int adults) {
        List<HotelEntity> result = new ArrayList<>();
        LocalDate checkInDate = LocalDate.parse(checkin);
        LocalDate checkOutDate = LocalDate.parse(checkin);
        if (!checkInDate.isBefore(LocalDate.now()) || !checkOutDate.isBefore(LocalDate.now())) {
            result = database.getHotelsBySearchId(kayakClient.getHotels(rooms, location, checkin, checkout, adults));
            if (result == null || result.isEmpty())
                result = new ArrayList<>();
        }
        return result;
    }

    public List<HotelEntity> getFilteredHotels(String responseId, Double rating, int stars, int priceMore, int priceLess) {
        List<HotelEntity> result = database.getFilteredHotels(responseId, rating, stars, priceMore, priceLess);
        if (result == null || result.isEmpty())
            result = new ArrayList<>();
        return result;
    }

    public List<HotelEntity> getHotelSearchHistory() {
        List<HotelEntity> result = database.getSearchHistory();
        if (result == null || result.isEmpty())
            result = new ArrayList<>();
        return result;
    }

    public Integer getLocations(String location) {
        return kayakClient.getHotelLocationId(location);
    }

    public HotelEntityLite getMostSearchedLocation() {
        HotelEntityLite result = new HotelEntityLite("");
        HotelEntityLite entity = database.getMostSearchedLocation();
        if (entity != null) {
            result = entity;
            database.saveMostSearchedLocation();
        }
        return result;
    }

    public void saveHotelFilters(HotelFilters hotelFilters) {
        database.saveHotelFilters(hotelFilters);
    }
}
