package com.kodilla.backend.controller;

import com.kodilla.backend.client.kayak.KayakClient;
import com.kodilla.backend.domain.dto.hotel.HotelListDto;
import com.kodilla.backend.mapper.mappers.HotelMapper;
import com.kodilla.backend.service.database.HotelDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/hotels")
public class HotelController {

    @Autowired
    private KayakClient kayakClient;

    @Autowired
    private HotelMapper mapper;

    @Autowired
    private HotelDatabase database;

    @GetMapping
    public List<HotelListDto> getHotels(@RequestParam int rooms, @RequestParam String location,
                                        @RequestParam String checkin, @RequestParam String checkout,
                                        @RequestParam int adults) {
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

    @GetMapping("/filter/{responseId}/")
    public List<HotelListDto> getHotels(@PathVariable String responseId, @RequestParam Double rating, @RequestParam int stars,
                                        @RequestParam int priceMore, @RequestParam int priceLess) {
        List<HotelListDto> result = mapper.mapToHotelListDto(database.getFilteredHotels(responseId, rating, stars, priceMore, priceLess));
        if (result == null || result.isEmpty())
            result = new ArrayList<>();
        return result;
    }


    @GetMapping("/history")
    public List<HotelListDto> getHotelSearchHistory() {
        List<HotelListDto> result = mapper.mapToHotelListDto(database.getSearchHistory());
        if (result == null || result.isEmpty())
            result = new ArrayList<>();
        return result;
    }

    @GetMapping("/locations/{location}")
    public Integer getLocations(@PathVariable String location) {
        return kayakClient.getHotelLocationId(location);
    }

}
