package com.kodilla.backend.repository.hotel;

import com.kodilla.backend.domain.entity.hotel.HotelListEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface HotelListRepo extends CrudRepository<HotelListEntity, String> {

    HotelListEntity save(HotelListEntity entity);

    List<HotelListEntity> findAll();

    @Query(nativeQuery = true)
    List<HotelListEntity> retrieveFilteredHotels(@Param("RESPONSEID") String responseId, @Param("RATING") double rating,
                                                 @Param("STARS") int stars, @Param("PRICEMORE") int pricemore,
                                                 @Param("PRICELESS") int priceless);

    List<HotelListEntity> findByHotelEntity_SearchId(String hotelEntityId);
}
