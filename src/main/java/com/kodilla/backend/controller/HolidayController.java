package com.kodilla.backend.controller;

import com.kodilla.backend.client.kayak.KayakClient;
import com.kodilla.backend.client.skyscanner.SkyscannerClient;
import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.dto.holiday.HolidayDto;
import com.kodilla.backend.domain.dto.hotel.HotelListDto;
import com.kodilla.backend.mapper.FlightMapper;
import com.kodilla.backend.mapper.HotelMapper;
import com.kodilla.backend.service.FlightDatabase;
import com.kodilla.backend.service.HotelDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class HolidayController {

    @Autowired
    private KayakClient kayakClient;

    @Autowired
    private SkyscannerClient skyscannerClient;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private HotelDatabase hotelDatabase;

    @Autowired
    private FlightDatabase flightDatabase;

    @Autowired
    private FlightMapper flightMapper;

    @GetMapping("/holiday")
    public HolidayDto getHoliday(@RequestParam int rooms, @RequestParam String originPlace,
                                 @RequestParam String destinationPlace, @RequestParam String checkin,
                                 @RequestParam String checkout, @RequestParam int adults) {
        List<HotelListDto> hotels = hotelMapper.mapToHotelListDto(hotelDatabase.getHotelsBySearchId(kayakClient.getHotels(rooms, destinationPlace, checkin, checkout, adults)));
        List<FlightDto> tripFlights = flightMapper.mapToFlightDtoList((flightDatabase.getFlightResponseById(skyscannerClient.getFlights(originPlace, destinationPlace, checkin)).get()));
        List<FlightDto> returnFlights = flightMapper.mapToFlightDtoList((flightDatabase.getFlightResponseById(skyscannerClient.getFlights(destinationPlace, originPlace, checkout)).get()));
        return new HolidayDto(hotels, tripFlights, returnFlights);
    }


}
