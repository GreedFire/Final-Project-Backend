package com.kodilla.backend.repository.flight;

import com.kodilla.backend.domain.entity.flight.location.FlightLocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface FlightLocationRepo extends CrudRepository<FlightLocationEntity, Long> {

    @Override
    FlightLocationEntity save(FlightLocationEntity entity);

    Optional<FlightLocationEntity> findByPlaceId(String placeId);

    List<FlightLocationEntity> findByWritedLocation(String writedLocation);
}
