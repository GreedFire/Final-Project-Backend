package com.kodilla.backend.repository.flight;

import com.kodilla.backend.domain.entity.flight.FlightCarriersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface FlightCarriersRepo extends CrudRepository<FlightCarriersEntity, Long> {

    List<FlightCarriersEntity> findByFlightReponseEntity_Id(long flightResponseId);
}
