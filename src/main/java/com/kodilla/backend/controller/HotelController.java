package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.hotel.HotelFiltersDto;
import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelLiteDto;
import com.kodilla.backend.facade.HotelFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/hotels")
public class HotelController {

    @Autowired
    private HotelFacade facade;

    @GetMapping
    public List<HotelDto> getHotels(@RequestParam int rooms, @RequestParam String location,
                                    @RequestParam String checkin, @RequestParam String checkout,
                                    @RequestParam int adults) {
        return facade.getHotels(rooms, location, checkin, checkout, adults);
    }

    @GetMapping("/filter/{responseId}/")
    public List<HotelDto> getFilteredHotels(@PathVariable String responseId, @RequestParam Double rating, @RequestParam int stars,
                                            @RequestParam int priceMore, @RequestParam int priceLess) {
        return facade.getFilteredHotels(responseId, rating, stars, priceMore, priceLess);
    }


    @GetMapping("/history")
    public List<HotelDto> getHotelSearchHistory() {
        return facade.getHotelSearchHistory();
    }

    @GetMapping("/locations/{location}")
    public Integer getLocations(@PathVariable String location) {
        return facade.getLocations(location);
    }

    @GetMapping("/location/mostInterested")
    public HotelLiteDto getMostSearchedLocation(){
        return facade.getMostSearchedLocation();
    }

    @PostMapping("/filter")
    public void saveHotelFilters(@RequestBody HotelFiltersDto hotelFiltersDto){
        facade.saveHotelFilters(hotelFiltersDto);
    }

}
