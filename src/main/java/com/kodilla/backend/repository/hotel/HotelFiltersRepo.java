package com.kodilla.backend.repository.hotel;

import com.kodilla.backend.domain.entity.hotel.HotelFilters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface HotelFiltersRepo extends CrudRepository<HotelFilters, Long> {

    @Override
    HotelFilters save(HotelFilters filters);
}
