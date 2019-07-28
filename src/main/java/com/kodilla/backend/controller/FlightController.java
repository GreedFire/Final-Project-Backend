package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.FlightFiltersDto;
import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/flights")
public class FlightController {

    @Autowired
    private FlightService service;

    @GetMapping
    public List<FlightDto> getFlights(@RequestParam String originPlace,
                                      @RequestParam String destinationPlace,
                                      @RequestParam String outboundPartialDate) {
        return service.getFlights(originPlace, destinationPlace, outboundPartialDate);
    }

    @GetMapping("filter/{responseId}/")
    public List<FlightDto> getFilteredFlights(@PathVariable long responseId,
                                              @RequestParam String carrierClass,
                                              @RequestParam int priceMoreThan,
                                              @RequestParam int priceLessThan) {
        return service.getFilteredFlights(responseId, carrierClass, priceMoreThan, priceLessThan);
    }

    @GetMapping("/locations/{place}")
    public String getFlightsLocation(@PathVariable String place) {
        return service.getFlightsLocation(place);
    }

    @GetMapping("/history")
    public List<FlightDto> getHotelSearchHistory() {
        return service.getHotelSearchHistory();
    }

    @PostMapping("/filter")
    public void saveFlightFilters(@RequestBody FlightFiltersDto flightFiltersDto){
        service.saveFlightFilters(flightFiltersDto);
    }


}
