package com.kodilla.backend.service;

import com.kodilla.backend.domain.entity.flight.location.FlightLocationEntity;
import com.kodilla.backend.repository.flight.FlightLocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightDatabase {

    @Autowired
    private FlightLocationRepo flightLocationRepo;

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
}
