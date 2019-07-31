package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.flight.FlightCarriersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface FlightCarriersRepo extends CrudRepository<FlightCarriersEntity, Long> {

    List<FlightCarriersEntity> findByFlightReponseEntity_Id(long flightResponseId);

    @Query(nativeQuery = true)
    List<FlightCarriersEntity> retrieveFilteredFlights(@Param("RESPONSEID") long responseId,
                                                       @Param("PLANECLASS") String planeClass,
                                                       @Param("PRICEMORE") int priceMore,
                                                       @Param("PRICELESS") int priceLess);
}
