package com.kodilla.backend.repository.hotel;

import com.kodilla.backend.domain.entity.hotel.HotelEntityLite;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface HotelEntityLiteRepo extends CrudRepository<HotelEntityLite, String> {

    @Override
    HotelEntityLite save(HotelEntityLite entityLite);

    @Query(nativeQuery = true)
    HotelEntityLite retrieveMostSearchedLocation();

    @Modifying
    @Query(nativeQuery = true)
    void saveMostSearchedLocation();
}
