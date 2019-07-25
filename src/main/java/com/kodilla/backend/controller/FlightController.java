package com.kodilla.backend.controller;

import com.kodilla.backend.client.skyscanner.SkyscannerClient;
import com.kodilla.backend.domain.dto.flight.FlightCarriersDto;
import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import com.kodilla.backend.mapper.mappers.FlightMapper;
import com.kodilla.backend.service.database.FlightDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/flights")
public class FlightController {

    @Autowired
    private SkyscannerClient skyscannerClient;

    @Autowired
    private FlightDatabase database;

    @Autowired
    private FlightMapper mapper;

    @GetMapping
    public List<FlightDto> getFlights(@RequestParam String originPlace,
                                      @RequestParam String destinationPlace,
                                      @RequestParam String outboundPartialDate) {
        List<FlightDto> result = new ArrayList<>();
        LocalDate date = LocalDate.parse(outboundPartialDate);
        if (!date.isBefore(LocalDate.now())) {
            long flightId = skyscannerClient.getFlights(originPlace, destinationPlace, outboundPartialDate);
            if (flightId != -1) {
                Optional<FlightReponseEntity> flightResponse = database.getFlightResponseById(flightId);
                if (flightResponse.isPresent())
                    result = mapper.mapToFlightDtoList(flightResponse.get());
            }
        }
        return result;
    }

    @GetMapping("filter/{responseId}/")
    public List<FlightDto> getFlights(@PathVariable long responseId,
                                      @RequestParam String carrierClass,
                                      @RequestParam int priceMoreThan,
                                      @RequestParam int priceLessThan) {
        List<FlightDto> resultList = new ArrayList<>();

        Optional<FlightReponseEntity> flightReponseEntity = database.getFlightResponseById(responseId);
        if (flightReponseEntity.isPresent()) {
            FlightDto flightDto = mapper.mapToFlightDto(flightReponseEntity.get());
            List<FlightCarriersDto> carriersListDto = mapper.mapToFlightCarriersListDto(
                    database.getFilteredFlights(responseId, carrierClass, priceMoreThan, priceLessThan));
            flightDto.setCarriers(carriersListDto);
            resultList.add(flightDto);
        }

        return resultList;
    }

    @GetMapping("/locations/{place}")
    public String getFlightsLocation(@PathVariable String place) {
        return skyscannerClient.getFlightLocationCode(place);
    }

    @GetMapping("/history")
    public List<FlightDto> getHotelSearchHistory() {
        return mapper.mapToFlightDtoList(database.getSearchHistory());
    }


}
