package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.client.skyscanner.SkyscannerClient;
import com.kodilla.backend.mapper.FlightMapper;
import com.kodilla.backend.service.FlightDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class FlightController {

    @Autowired
    private SkyscannerClient skyscannerClient;

    @Autowired
    private FlightDatabase database;

    @Autowired
    private FlightMapper mapper;

    @GetMapping("/flights")
    public FlightDto getFlights(@RequestParam String originPlace,
                                @RequestParam String destinationPlace,
                                @RequestParam String outboundPartialDate){
        return skyscannerClient.getFlights(originPlace, destinationPlace, outboundPartialDate);
    }

    @GetMapping("/flights/locations")
    public String getFlightsLocation(@RequestParam String place){
        return skyscannerClient.getFlightLocationCode(place);
    }

    @GetMapping("/flights/history")
    public List<FlightDto> getHotelSearchHistory(){
        return mapper.mapToFlightDtoList(database.getSearchHistory());
    }



}
