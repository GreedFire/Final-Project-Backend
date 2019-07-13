package com.kodilla.backend.repository.hotel;

import com.kodilla.backend.domain.entity.hotel.HotelLocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface HotelLocationRepo extends CrudRepository<HotelLocationEntity, Long> {

    @Override
    HotelLocationEntity save(HotelLocationEntity hotelLocationEntity);

    Optional<HotelLocationEntity> findByCityId(int cityId);

    List<HotelLocationEntity> findByWritedCity(String city);
}
