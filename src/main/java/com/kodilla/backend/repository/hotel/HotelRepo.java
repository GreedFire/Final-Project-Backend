package com.kodilla.backend.repository.hotel;

import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public interface HotelRepo extends CrudRepository<HotelEntity, String> {

    HotelEntity save(HotelEntity entity);



    List<HotelEntity> findAllBySearchDateAfter(LocalDate date);

    @Query(nativeQuery = true)
    List<HotelEntity> retrieveFilteredHotels(@Param("RESPONSEID") String responseId, @Param("RATING") double rating,
                                             @Param("STARS") int stars, @Param("PRICEMORE") int pricemore,
                                             @Param("PRICELESS") int priceless);

    List<HotelEntity> findByHotelResponseEntity_SearchId(String hotelEntityId);
}
