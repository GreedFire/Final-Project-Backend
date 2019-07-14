package com.kodilla.backend.repository.flight;

import com.kodilla.backend.domain.entity.flight.flights.FlightReponseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Repository
public interface FlightResponseRepo extends CrudRepository<FlightReponseEntity, Long> {

    @Override
    FlightReponseEntity save(FlightReponseEntity entity);

    List<FlightReponseEntity> findByDepartureDateAndOriginAndDestination(LocalDateTime departureDate, String origin, String destination);

    List<FlightReponseEntity> findAll();

}
