package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface HotelEntityDao extends CrudRepository<HotelEntity, String> {
    @Override
    HotelEntity save(HotelEntity entity);

}


// SPRAWDŹ CZY TABLICE MOZNA ZAMIENIC NA LISTY