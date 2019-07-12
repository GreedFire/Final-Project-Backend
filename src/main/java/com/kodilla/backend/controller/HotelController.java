package com.kodilla.backend.controller;

import com.kodilla.backend.client.kayak.KayakClient;
import com.kodilla.backend.domain.dto.hotel.HotelLocationDto;
import com.kodilla.backend.domain.dto.hotel.HotelSetDto;
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
    private HotelMapper hotelMapper;

    @Autowired
    private HotelDatabase database;

    @GetMapping("/hotels")
    public List<HotelSetDto> getHotels(@RequestParam int rooms, @RequestParam String location,
                                       @RequestParam String checkin, @RequestParam String checkout,
                                       @RequestParam int adults){

        return kayakClient.getHotels(rooms, location, checkin, checkout, adults).getHotels();
    }

    @GetMapping("/hotels/locations")
    public int getLocations(@RequestParam String location){
        return kayakClient.getHotelLocationId(location);
    }

}
