package com.kodilla.backend.service;

import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import com.kodilla.backend.domain.entity.hotel.HotelListEntity;
import com.kodilla.backend.domain.entity.hotel.HotelLocationEntity;
import com.kodilla.backend.repository.hotel.HotelListRepo;
import com.kodilla.backend.repository.hotel.HotelLocationRepo;
import com.kodilla.backend.repository.hotel.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelDatabase {

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private HotelLocationRepo hotelLocationRepo;

    @Autowired
    private HotelListRepo hotelListRepo;

    public HotelEntity saveHotel(HotelEntity hotelEntity) {
        return hotelRepo.save(hotelEntity);
    }

    public List<HotelListEntity> getSearchHistory() {
        return hotelListRepo.findAll();
    }

    public void saveHotelLocations(HotelLocationEntity location) {
        if (!hotelLocationRepo.findByCityId(location.getCityId()).isPresent()) {
            hotelLocationRepo.save(location);
        }
    }

    public Optional<HotelLocationEntity> getHotelLocationByCityId(int cityId) {
        return hotelLocationRepo.findByCityId(cityId);
    }

    public List<HotelLocationEntity> getHotelLocationByLocationName(String location) {
        return hotelLocationRepo.findByWritedCity(location);
    }
}
