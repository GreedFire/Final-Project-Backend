package com.kodilla.backend.facade;

import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelFiltersDto;
import com.kodilla.backend.domain.dto.hotel.HotelLiteDto;
import com.kodilla.backend.mapper.HotelMapper;
import com.kodilla.backend.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelFacade.class);

    @Autowired
    private HotelMapper mapper;

    @Autowired
    private HotelService service;

    public List<HotelDto> getHotels(int rooms, String location, String checkin, String checkout, int adults) {
        LOGGER.info("Getting hotels from service");
        return mapper.mapToHotelListDto(service.getHotels(rooms, location, checkin, checkout, adults));
    }

    public List<HotelDto> getFilteredHotels(String responseId, Double rating, int stars, int priceMore, int priceLess) {
        LOGGER.info("Getting filtered hotels from service");
        return mapper.mapToHotelListDto(service.getFilteredHotels(responseId, rating, stars, priceMore, priceLess));
    }

    public List<HotelDto> getHotelSearchHistory() {
        LOGGER.info("Getting hotel search history from service");
        return mapper.mapToHotelListDto(service.getHotelSearchHistory());
    }

    public Integer getLocations(String location) {
        LOGGER.info("Getting location from service");
        return service.getLocations(location);
    }

    public HotelLiteDto getMostSearchedLocation() {
        LOGGER.info("Getting most searched location from service");
        return mapper.mapToHotelLiteDto(service.getMostSearchedLocation());
    }

    public void saveHotelFilters(HotelFiltersDto hotelFiltersDto) {
        LOGGER.info("Saving hotel filters from service");
        service.saveHotelFilters(mapper.mapToHotelFiltersEntity(hotelFiltersDto));
    }


}
