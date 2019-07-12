package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelLocationDto;
import com.kodilla.backend.domain.dto.hotel.HotelSetDto;
import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import com.kodilla.backend.domain.entity.hotel.HotelLocationEntity;
import com.kodilla.backend.domain.entity.hotel.HotelSetEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    public HotelEntity mapToHotelEntity(HotelDto hotelDto){
        HotelEntity hotelResponseResult = new HotelEntity(
                hotelDto.getSearchId(),
                hotelDto.getCurrency(),
                hotelDto.getDestinationLocation(),
                hotelDto.getShareURL()
        );

        List<HotelSetEntity> hotelListEntity = hotelDto.getHotels().stream()
                .map(hotelset -> new HotelSetEntity(
                        hotelset.getId(),
                        Double.parseDouble(hotelset.getUserRating()),
                        new BigDecimal(hotelset.getPrice().substring(1)),
                        hotelset.getStars(),
                        hotelset.getName(),
                        hotelset.getPhone(),
                        hotelset.getAddress(),
                        hotelset.getCity(),
                        hotelset.getCountry(),
                        hotelset.getDisplayaddress(),
                        hotelset.getThumburl(),
                        hotelResponseResult
                )).collect(Collectors.toList());

        hotelResponseResult.setHotels(hotelListEntity);

         return hotelResponseResult;
    }

    public List<HotelSetEntity> mapToHotelSetEntity(List<HotelSetDto> hotels){
         List<HotelSetEntity> result = hotels.stream()
                .map(hotelset -> new HotelSetEntity(
                        hotelset.getId(),
                        Double.parseDouble(hotelset.getUserRating()),
                        new BigDecimal(hotelset.getPrice().substring(1)),
                        hotelset.getStars(),
                        hotelset.getName(),
                        hotelset.getPhone(),
                        hotelset.getAddress(),
                        hotelset.getCity(),
                        hotelset.getCountry(),
                        hotelset.getDisplayaddress(),
                        hotelset.getThumburl()
                )).collect(Collectors.toList());
         return result;
    }

    public HotelLocationEntity mapToHotelLocations(HotelLocationDto hotelLocationDto, String writedCity){
       return new HotelLocationEntity(
               hotelLocationDto.getCityId(),
               writedCity
       );
    }
}
