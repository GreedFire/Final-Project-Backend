package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.HolidayDto;
import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.service.FlightService;
import com.kodilla.backend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/holiday")
public class HolidayController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private FlightService flightService;


    @GetMapping
    public HolidayDto getHoliday(@RequestParam int rooms, @RequestParam String originPlace,
                                 @RequestParam String destinationPlace, @RequestParam String checkin,
                                 @RequestParam String checkout, @RequestParam int adults) {
        List<HotelDto> hotels = hotelService.getHotels(rooms, destinationPlace, checkin, checkout, adults);
        List<FlightDto> tripFlights = flightService.getFlights(originPlace, destinationPlace, checkin);
        List<FlightDto> returnFlights = flightService.getFlights(destinationPlace, originPlace, checkout);

        return new HolidayDto(hotels, tripFlights, returnFlights);
    }
}
