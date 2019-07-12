package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.hotel.HotelLocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface HotelLocationDao extends CrudRepository<HotelLocationEntity, Integer> {

    @Override
    HotelLocationEntity save(HotelLocationEntity hotelLocationEntity);

    Optional<HotelLocationEntity> findByCityId(int cityId);

    Optional<HotelLocationEntity> findByWritedCity(String city);
}
