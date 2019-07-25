package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.hotel.HotelListDto;
import com.kodilla.backend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/hotels")
public class HotelController {

    @Autowired
    private HotelService service;

    @GetMapping
    public List<HotelListDto> getHotels(@RequestParam int rooms, @RequestParam String location,
                                        @RequestParam String checkin, @RequestParam String checkout,
                                        @RequestParam int adults) {
        return service.getHotels(rooms, location, checkin, checkout, adults);
    }

    @GetMapping("/filter/{responseId}/")
    public List<HotelListDto> getFilteredHotels(@PathVariable String responseId, @RequestParam Double rating, @RequestParam int stars,
                                                @RequestParam int priceMore, @RequestParam int priceLess) {
        return service.getFilteredHotels(responseId, rating, stars, priceMore, priceLess);
    }


    @GetMapping("/history")
    public List<HotelListDto> getHotelSearchHistory() {
        return service.getHotelSearchHistory();
    }

    @GetMapping("/locations/{location}")
    public Integer getLocations(@PathVariable String location) {
        return service.getLocations(location);
    }

}
