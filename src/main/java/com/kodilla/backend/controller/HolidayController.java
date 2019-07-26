package com.kodilla.backend.controller;

import com.kodilla.backend.client.kayak.KayakClient;
import com.kodilla.backend.client.skyscanner.SkyscannerClient;
import com.kodilla.backend.domain.dto.HolidayDto;
import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.dto.hotel.HotelListDto;
import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import com.kodilla.backend.mapper.mappers.FlightMapper;
import com.kodilla.backend.mapper.mappers.HotelMapper;
import com.kodilla.backend.service.database.FlightDatabase;
import com.kodilla.backend.service.database.HotelDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<HotelListDto> hotels = new ArrayList<>();
        LocalDate checkInHotelsDate = LocalDate.parse(checkin);
        LocalDate checkOutHotelsDate = LocalDate.parse(checkin);
        if (!checkInHotelsDate.isBefore(LocalDate.now()) || !checkOutHotelsDate.isBefore(LocalDate.now())) {
            hotels = hotelMapper.mapToHotelListDto(hotelDatabase.getHotelsBySearchId(kayakClient.getHotels(rooms, destinationPlace, checkin, checkout, adults)));
            if (hotels == null || hotels.isEmpty())
                hotels = new ArrayList<>();
        }

        List<FlightDto> tripFlights = new ArrayList<>();
        LocalDate checkInDate = LocalDate.parse(checkin);
        if (!checkInDate.isBefore(LocalDate.now())) {
            long flightId = skyscannerClient.getFlights(originPlace, destinationPlace, checkin);
            if (flightId != -1) {
                Optional<FlightReponseEntity> flightResponse = flightDatabase.getFlightResponseById(flightId);
                if (flightResponse.isPresent())
                    tripFlights = flightMapper.mapToFlightDtoList(flightResponse.get());
            }
        }

        List<FlightDto> returnFlights = new ArrayList<>();
        LocalDate checkOutDate = LocalDate.parse(checkout);
        if (!checkOutDate.isBefore(LocalDate.now())) {
            long flightId = skyscannerClient.getFlights(originPlace, destinationPlace, checkout);
            if (flightId != -1) {
                Optional<FlightReponseEntity> flightResponse = flightDatabase.getFlightResponseById(flightId);
                if (flightResponse.isPresent())
                    returnFlights = flightMapper.mapToFlightDtoList(flightResponse.get());
            }
        }
        return new HolidayDto(hotels, tripFlights, returnFlights);
    }
}
