package com.kodilla.backend.repository.hotel;

import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface HotelRepo extends CrudRepository<HotelEntity, String> {
    @Override
    HotelEntity save(HotelEntity entity);


}