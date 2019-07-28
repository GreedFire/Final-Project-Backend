package com.kodilla.backend.service;

import com.kodilla.backend.client.skyscanner.SkyscannerClient;
import com.kodilla.backend.domain.dto.flight.FlightFiltersDto;
import com.kodilla.backend.domain.dto.flight.FlightCarriersDto;
import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import com.kodilla.backend.mapper.FlightMapper;
import com.kodilla.backend.service.database.FlightDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private SkyscannerClient skyscannerClient;

    @Autowired
    private FlightDatabase database;

    @Autowired
    private FlightMapper mapper;

    public List<FlightDto> getFlights(String originPlace,
                                      String destinationPlace,
                                      String outboundPartialDate) {
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

    public List<FlightDto> getFilteredFlights(long responseId,
                                              String carrierClass,
                                              int priceMoreThan,
                                              int priceLessThan) {
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

    public String getFlightsLocation(String place) {
        return skyscannerClient.getFlightLocationCode(place);
    }

    public List<FlightDto> getHotelSearchHistory() {
        return mapper.mapToFlightDtoList(database.getSearchHistory());
    }

    public void saveFlightFilters(FlightFiltersDto flightFiltersDto){
        database.saveFlightFilters(mapper.mapToFlightFiltersEntity(flightFiltersDto));
    }

}
