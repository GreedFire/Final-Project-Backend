package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.hotel.HotelSetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface HotelSetDao extends CrudRepository<HotelSetEntity, String> {

    HotelSetEntity save(HotelSetEntity entity);
}
