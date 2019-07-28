package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.hotel.HotelFiltersDto;
import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelLiteDto;
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
    public List<HotelDto> getHotels(@RequestParam int rooms, @RequestParam String location,
                                    @RequestParam String checkin, @RequestParam String checkout,
                                    @RequestParam int adults) {
        return service.getHotels(rooms, location, checkin, checkout, adults);
    }

    @GetMapping("/filter/{responseId}/")
    public List<HotelDto> getFilteredHotels(@PathVariable String responseId, @RequestParam Double rating, @RequestParam int stars,
                                            @RequestParam int priceMore, @RequestParam int priceLess) {
        return service.getFilteredHotels(responseId, rating, stars, priceMore, priceLess);
    }


    @GetMapping("/history")
    public List<HotelDto> getHotelSearchHistory() {
        return service.getHotelSearchHistory();
    }

    @GetMapping("/locations/{location}")
    public Integer getLocations(@PathVariable String location) {
        return service.getLocations(location);
    }

    @GetMapping("/location/mostInterested")
    public HotelLiteDto getMostSearchedLocation(){
        return service.getMostSearchedLocation();
    }

    @PostMapping("/filter")
    public void saveHotelFilters(@RequestBody HotelFiltersDto hotelFiltersDto){
        service.saveHotelFilters(hotelFiltersDto);
    }

}
