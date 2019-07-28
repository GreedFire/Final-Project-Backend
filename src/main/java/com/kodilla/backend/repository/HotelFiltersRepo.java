package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.HotelFilters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface HotelFiltersRepo extends CrudRepository<HotelFilters, Long> {

    @Override
    HotelFilters save(HotelFilters filters);
}
