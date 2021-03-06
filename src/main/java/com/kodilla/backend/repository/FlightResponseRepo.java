package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface FlightResponseRepo extends CrudRepository<FlightReponseEntity, Long> {

    @Override
    FlightReponseEntity save(FlightReponseEntity entity);

    List<FlightReponseEntity> findByDepartureDateAndOriginAndDestination(LocalDateTime departureDate, String origin, String destination);

    List<FlightReponseEntity> findAllBySearchDateAfter(LocalDate date);

    Optional<FlightReponseEntity> findById(long id);

}
