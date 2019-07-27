package com.kodilla.backend.service;

import com.kodilla.backend.client.kayak.KayakClient;
import com.kodilla.backend.domain.dto.hotel.HotelListDto;
import com.kodilla.backend.domain.dto.hotel.HotelLiteDto;
import com.kodilla.backend.domain.entity.hotel.HotelEntityLite;
import com.kodilla.backend.mapper.mappers.HotelMapper;
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

    public List<HotelListDto> getHotels(int rooms, String location, String checkin, String checkout, int adults) {
        List<HotelListDto> result = new ArrayList<>();
        LocalDate checkInDate = LocalDate.parse(checkin);
        LocalDate checkOutDate = LocalDate.parse(checkin);
        if (!checkInDate.isBefore(LocalDate.now()) || !checkOutDate.isBefore(LocalDate.now())) {
            result = mapper.mapToHotelListDto(database.getHotelsBySearchId(kayakClient.getHotels(rooms, location, checkin, checkout, adults)));
            if (result == null || result.isEmpty())
                result = new ArrayList<>();
        }

        return result;

    }

    public List<HotelListDto> getFilteredHotels(String responseId, Double rating, int stars, int priceMore, int priceLess) {
        List<HotelListDto> result = mapper.mapToHotelListDto(database.getFilteredHotels(responseId, rating, stars, priceMore, priceLess));
        if (result == null || result.isEmpty())
            result = new ArrayList<>();
        return result;
    }

    public List<HotelListDto> getHotelSearchHistory() {
        List<HotelListDto> result = mapper.mapToHotelListDto(database.getSearchHistory());
        if (result == null || result.isEmpty())
            result = new ArrayList<>();
        return result;
    }

    public Integer getLocations(String location) {
        return kayakClient.getHotelLocationId(location);
    }

    public HotelLiteDto getMostSearchedLocation(){
        HotelEntityLite entity = database.getMostSearchedLocation();
        if(entity != null){
            saveMostSearchedLocation(entity);
            return mapper.mapToHotelLiteDto(entity);
        }
        else return new HotelLiteDto("");

    }

    public void saveMostSearchedLocation(HotelEntityLite entityLite){
        database.saveMostSearchedLocation(entityLite);
    }
}
