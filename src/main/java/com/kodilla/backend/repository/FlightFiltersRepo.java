package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.flight.FlightFilters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface FlightFiltersRepo extends CrudRepository<FlightFilters, Long> {

    @Override
    FlightFilters save(FlightFilters filters);
}
