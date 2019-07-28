package com.kodilla.backend.service;

import com.kodilla.backend.client.kayak.KayakClient;
import com.kodilla.backend.domain.dto.hotel.HotelFiltersDto;
import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelLiteDto;
import com.kodilla.backend.domain.entity.hotel.HotelEntityLite;
import com.kodilla.backend.mapper.HotelMapper;
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
    private HotelMapper mapper;

    @Autowired
    private HotelDatabase database;

    public List<HotelDto> getHotels(int rooms, String location, String checkin, String checkout, int adults) {
        List<HotelDto> result = new ArrayList<>();
        LocalDate checkInDate = LocalDate.parse(checkin);
        LocalDate checkOutDate = LocalDate.parse(checkin);
        if (!checkInDate.isBefore(LocalDate.now()) || !checkOutDate.isBefore(LocalDate.now())) {
            result = mapper.mapToHotelListDto(database.getHotelsBySearchId(kayakClient.getHotels(rooms, location, checkin, checkout, adults)));
            if (result == null || result.isEmpty())
                result = new ArrayList<>();
        }

        return result;

    }

    public List<HotelDto> getFilteredHotels(String responseId, Double rating, int stars, int priceMore, int priceLess) {
        List<HotelDto> result = mapper.mapToHotelListDto(database.getFilteredHotels(responseId, rating, stars, priceMore, priceLess));
        if (result == null || result.isEmpty())
            result = new ArrayList<>();
        return result;
    }

    public List<HotelDto> getHotelSearchHistory() {
        List<HotelDto> result = mapper.mapToHotelListDto(database.getSearchHistory());
        if (result == null || result.isEmpty())
            result = new ArrayList<>();
        return result;
    }

    public Integer getLocations(String location) {
        return kayakClient.getHotelLocationId(location);
    }

    public HotelLiteDto getMostSearchedLocation(){
        HotelLiteDto result = new HotelLiteDto("");
        HotelEntityLite entity = database.getMostSearchedLocation();
        if(entity != null){
            result =  mapper.mapToHotelLiteDto(entity);
            database.saveMostSearchedLocation();
        }

        return result;
    }

    public void saveHotelFilters(HotelFiltersDto hotelFiltersDto){
        database.saveHotelFilters(mapper.mapToHotelFiltersEntity(hotelFiltersDto));
    }
}
