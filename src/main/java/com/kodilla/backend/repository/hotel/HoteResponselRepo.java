package com.kodilla.backend.repository.hotel;

import com.kodilla.backend.domain.entity.hotel.HotelResponseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface HoteResponselRepo extends CrudRepository<HotelResponseEntity, String> {

    @Override
    HotelResponseEntity save(HotelResponseEntity entity);

}