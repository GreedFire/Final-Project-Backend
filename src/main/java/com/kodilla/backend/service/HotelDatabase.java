package com.kodilla.backend.service;

import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import com.kodilla.backend.domain.entity.hotel.HotelLocationEntity;
import com.kodilla.backend.repository.HotelEntityDao;
import com.kodilla.backend.repository.HotelLocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelDatabase {

    @Autowired
    private HotelEntityDao hotelEntityDao;

    @Autowired
    private HotelLocationDao hotelLocationDao;

    public HotelEntity saveHotel(HotelEntity hotelEntity){
        return hotelEntityDao.save(hotelEntity);
    }

    public void saveHotelLocations(HotelLocationEntity location){
            if( !hotelLocationDao.findByCityId(location.getCityId()).isPresent() ){
                hotelLocationDao.save(location);
            }
    }

    public Optional<HotelLocationEntity> getHotelLocationById(int cityId){
        return hotelLocationDao.findByCityId(cityId);
    }

    public Optional<HotelLocationEntity> getHotelLocationByLocationName(String location){
        return hotelLocationDao.findByWritedCity(location);
    }
}
