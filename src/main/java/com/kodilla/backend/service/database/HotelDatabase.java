package com.kodilla.backend.service.database;

import com.kodilla.backend.domain.entity.hotel.HotelFilters;
import com.kodilla.backend.domain.entity.hotel.HotelResponseEntity;
import com.kodilla.backend.domain.entity.hotel.HotelEntityLite;
import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import com.kodilla.backend.domain.entity.hotel.HotelLocationEntity;
import com.kodilla.backend.repository.hotel.HotelFiltersRepo;
import com.kodilla.backend.repository.hotel.HotelEntityLiteRepo;
import com.kodilla.backend.repository.hotel.HotelRepo;
import com.kodilla.backend.repository.hotel.HotelLocationRepo;
import com.kodilla.backend.repository.hotel.HoteResponselRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HotelDatabase {

    @Autowired
    private HoteResponselRepo hoteResponselRepo;

    @Autowired
    private HotelLocationRepo hotelLocationRepo;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private HotelEntityLiteRepo hotelEntityLiteRepo;

    @Autowired
    private HotelFiltersRepo hotelFiltersRepo;

    public HotelResponseEntity saveHotel(HotelResponseEntity hotelResponseEntity) {
        return hoteResponselRepo.save(hotelResponseEntity);
    }

    public List<HotelEntity> getSearchHistory() {
        return hotelRepo.findAllBySearchDateAfter(LocalDate.now().minusDays(1));
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

    public List<HotelEntity> getFilteredHotels(String responseId, Double rating, int stars,
                                               int priceMore, int priceLess) {
        return hotelRepo.retrieveFilteredHotels(responseId, rating, stars, priceMore, priceLess);
    }

    public List<HotelEntity> getHotelsBySearchId(String searchId) {
        return hotelRepo.findByHotelResponseEntity_SearchId(searchId);
    }

    public HotelEntityLite getMostSearchedLocation(){
        return hotelEntityLiteRepo.retrieveMostSearchedLocation();
    }

    public void saveMostSearchedLocation(){
        hotelEntityLiteRepo.saveMostSearchedLocation();
    }

    public void saveHotelFilters(HotelFilters hotelFilters){
        hotelFiltersRepo.save(hotelFilters);
    }
}
