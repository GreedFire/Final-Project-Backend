package com.kodilla.backend.service.database;

import com.kodilla.backend.domain.entity.flight.FlightFilters;
import com.kodilla.backend.domain.entity.flight.FlightCarriersEntity;
import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import com.kodilla.backend.domain.entity.flight.location.FlightLocationEntity;
import com.kodilla.backend.repository.FlightFiltersRepo;
import com.kodilla.backend.repository.FlightCarriersRepo;
import com.kodilla.backend.repository.FlightLocationRepo;
import com.kodilla.backend.repository.FlightResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightDatabase {

    @Autowired
    private FlightLocationRepo flightLocationRepo;

    @Autowired
    private FlightResponseRepo flightResponseRepo;

    @Autowired
    private FlightCarriersRepo flightCarriersRepo;

    @Autowired
    private FlightFiltersRepo flightFiltersRepo;

    public FlightReponseEntity saveFlightResponse(FlightReponseEntity entity) {
        return flightResponseRepo.save(entity);
    }

    public void saveFlightLocations(FlightLocationEntity location) {
        if (!flightLocationRepo.findByPlaceId(location.getPlaceId()).isPresent()) {
            flightLocationRepo.save(location);
        }
    }

    public Optional<FlightLocationEntity> getFlightLocationByPlaceId(String placeId) {
        return flightLocationRepo.findByPlaceId(placeId);
    }

    public List<FlightLocationEntity> getFlightLocationByWritedLocation(String writedLocation) {
        return flightLocationRepo.findByWritedLocation(writedLocation);
    }

    public List<FlightReponseEntity> getFlightsByDepartureDateAndOriginAndDestination(LocalDateTime departureDate, String origin, String destination) {
        return flightResponseRepo.findByDepartureDateAndOriginAndDestination(departureDate, origin, destination);
    }

    public List<FlightReponseEntity> getSearchHistory() {
        return flightResponseRepo.findAllBySearchDateAfter(LocalDate.now().minusDays(1));
    }

    public List<FlightCarriersEntity> getFlightsCariers(long flightResponseId) {
        return flightCarriersRepo.findByFlightReponseEntity_Id(flightResponseId);
    }

    public Optional<FlightReponseEntity> getFlightResponseById(long id) {
        return flightResponseRepo.findById(id);
    }

    public List<FlightCarriersEntity> getFilteredFlights(long responseId, String planeClass, int priceMoreThan, int priceLessThan) {
        return flightCarriersRepo.retrieveFilteredFlights(responseId, planeClass, priceMoreThan, priceLessThan);
    }

    public void saveFlightFilters(FlightFilters flightFilters){
        flightFiltersRepo.save(flightFilters);
    }
}
