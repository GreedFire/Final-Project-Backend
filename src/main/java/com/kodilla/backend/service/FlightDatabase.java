package com.kodilla.backend.service;

import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import com.kodilla.backend.domain.entity.flight.location.FlightLocationEntity;
import com.kodilla.backend.repository.flight.FlightLocationRepo;
import com.kodilla.backend.repository.flight.FlightResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightDatabase {

    @Autowired
    private FlightLocationRepo flightLocationRepo;

    @Autowired
    private FlightResponseRepo flightResponseRepo;

    public FlightReponseEntity saveFlightResponse(FlightReponseEntity entity){
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

    public List<FlightReponseEntity> getFlightsByDepartureDateAndOriginAndDestination(LocalDateTime departureDate, String origin, String destination){
        return flightResponseRepo.findByDepartureDateAndOriginAndDestination(departureDate, origin, destination);
    }

    public List<FlightReponseEntity> getSearchHistory(){
        return flightResponseRepo.findAll();
    }
}
