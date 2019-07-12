package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.flight.flights.FlightReponseDto;
import com.kodilla.backend.client.skyscanner.SkyscannerClient;
import com.kodilla.backend.domain.dto.flight.location.FlightLocationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class FlightController {

    @Autowired
    private SkyscannerClient skyscannerClient;

    @GetMapping("/flights")
    public FlightReponseDto getFlights(@RequestParam String originPlace, @RequestParam String destinationPlace, @RequestParam String outboundPartialDate, @RequestParam(required = false) String inboundPartialDate){

        return skyscannerClient.getFlights(originPlace, destinationPlace, outboundPartialDate, inboundPartialDate);
    }

    @GetMapping("/flights/locations")
    public FlightLocationResponseDto getFlightsLocation(@RequestParam String place){

        return skyscannerClient.getFlightLocationCode(place);
    }



}
