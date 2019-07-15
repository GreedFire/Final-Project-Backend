package com.kodilla.backend.controller;

import com.kodilla.backend.client.kayak.KayakClient;
import com.kodilla.backend.domain.dto.hotel.HotelListDto;
import com.kodilla.backend.mapper.HotelMapper;
import com.kodilla.backend.service.HotelDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class HotelController {

    @Autowired
    private KayakClient kayakClient;

    @Autowired
    private HotelMapper mapper;

    @Autowired
    private HotelDatabase database;

    @GetMapping("/hotels")
    public List<HotelListDto> getHotels(@RequestParam int rooms, @RequestParam String location,
                                        @RequestParam String checkin, @RequestParam String checkout,
                                        @RequestParam int adults) {
        return mapper.mapToHotelListDto(database.getHotelsBySearchId(kayakClient.getHotels(rooms,location,checkin,checkout,adults)));
    }

    @GetMapping("/hotels/filter")
    public List<HotelListDto> getHotels(@RequestParam String responseId, @RequestParam Double rating, @RequestParam int stars,
                                        @RequestParam int priceMore, @RequestParam int priceLess) {
        return mapper.mapToHotelListDto(database.getFilteredHotels(responseId, rating, stars, priceMore, priceLess));
    }


    @GetMapping("/hotels/history")
    public List<HotelListDto> getHotelSearchHistory() {
        return mapper.mapToHotelListDto(database.getSearchHistory());
    }

    @GetMapping("/hotels/locations")
    public int getLocations(@RequestParam String location) {
        return kayakClient.getHotelLocationId(location);
    }

}
