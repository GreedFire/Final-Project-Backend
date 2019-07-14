package com.kodilla.backend.controller;

import com.kodilla.backend.client.kayak.KayakClient;
import com.kodilla.backend.client.skyscanner.SkyscannerClient;
import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.dto.holiday.HolidayDto;
import com.kodilla.backend.domain.dto.hotel.HotelListDto;
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

    @GetMapping("/holiday")
    public HolidayDto getHoliday(@RequestParam int rooms, @RequestParam String originPlace,
                                 @RequestParam String destinationPlace, @RequestParam String checkin,
                                 @RequestParam String checkout, @RequestParam int adults) {
        List<HotelListDto> hotels = new ArrayList<>();
        int counter = 0;
        while (hotels.size() == 0) {
            hotels = kayakClient.getHotels(rooms, destinationPlace, checkin, checkout, adults).getHotels();
            counter++;
            if(counter == 3)
                break;
        }
        FlightDto tripFlights = skyscannerClient.getFlights(originPlace, destinationPlace, checkin);
        FlightDto returnFlight = skyscannerClient.getFlights(destinationPlace, originPlace, checkout);

        return new HolidayDto(hotels, tripFlights, returnFlight);
    }
}
