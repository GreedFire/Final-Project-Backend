package com.kodilla.backend.repository.hotel;

import com.kodilla.backend.domain.entity.hotel.HotelListEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface HotelListRepo extends CrudRepository<HotelListEntity, String> {

    HotelListEntity save(HotelListEntity entity);

    List<HotelListEntity> findAll();
}
