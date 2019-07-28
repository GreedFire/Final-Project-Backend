package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.hotel.HotelFiltersDto;
import com.kodilla.backend.domain.dto.hotel.HotelResponseDto;
import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelLiteDto;
import com.kodilla.backend.domain.dto.hotel.HotelLocationDto;
import com.kodilla.backend.domain.entity.hotel.HotelFilters;
import com.kodilla.backend.domain.entity.hotel.HotelEntity;
import com.kodilla.backend.domain.entity.hotel.HotelEntityLite;
import com.kodilla.backend.domain.entity.hotel.HotelListEntity;
import com.kodilla.backend.domain.entity.hotel.HotelLocationEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    public HotelEntity mapToHotelEntity(HotelResponseDto hotelResponseDto) {
        HotelEntity hotelResponseResult = new HotelEntity(
                hotelResponseDto.getSearchId(),
                hotelResponseDto.getCurrency(),
                hotelResponseDto.getDestinationLocation(),
                hotelResponseDto.getShareURL()
        );
        List<HotelListEntity> hotelListEntity = hotelResponseDto.getHotels().stream()
                .map(hotelset -> new HotelListEntity(
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

    public List<HotelDto> mapToHotelListDto(List<HotelListEntity> list) {
        return list.stream()
                .map(hotelset -> new HotelDto(
                        hotelset.getId(),
                        String.valueOf(hotelset.getUserRating()),
                        String.valueOf(hotelset.getPrice()),
                        hotelset.getStars(),
                        hotelset.getName(),
                        hotelset.getPhone(),
                        hotelset.getAddress(),
                        hotelset.getCity(),
                        hotelset.getCountry(),
                        hotelset.getDisplayaddress(),
                        hotelset.getThumburl(),
                        hotelset.getHotelEntity().getSearchId()
                )).collect(Collectors.toList());
    }

    public HotelResponseDto mapToHotelDto(HotelEntity hotelEntity) {
        HotelResponseDto hotelResponseDto = new HotelResponseDto(
                hotelEntity.getSearchId(),
                hotelEntity.getCurrency(),
                hotelEntity.getDestinationLocation(),
                hotelEntity.getShareURL()
        );

        List<HotelDto> hotelDto = hotelEntity.getHotels().stream()
                .map(hotelset -> new HotelDto(
                        hotelset.getId(),
                        String.valueOf(hotelset.getUserRating()),
                        String.valueOf(hotelset.getPrice()),
                        hotelset.getStars(),
                        hotelset.getName(),
                        hotelset.getPhone(),
                        hotelset.getAddress(),
                        hotelset.getCity(),
                        hotelset.getCountry(),
                        hotelset.getDisplayaddress(),
                        hotelset.getThumburl(),
                        hotelset.getHotelEntity().getSearchId()
                )).collect(Collectors.toList());
        hotelResponseDto.setHotels(hotelDto);
        return hotelResponseDto;
    }

    public HotelLocationEntity mapToHotelLocationsEntity(HotelLocationDto hotelLocationDto, String writedCity) {
        return new HotelLocationEntity(
                hotelLocationDto.getCityId(),
                writedCity
        );
    }

    public HotelLocationDto mapToHotelLocationsDto(HotelLocationEntity hotelLocationEntity) {
        return new HotelLocationDto(
                hotelLocationEntity.getCityId()
        );
    }

    public List<HotelLocationEntity> mapToHotelLocationEntityList(List<HotelLocationDto> hotelLocationDtoList, String writedLocation) {
        return hotelLocationDtoList.stream()
                .map(location -> new HotelLocationEntity(
                        location.getCityId(),
                        writedLocation
                ))
                .collect(Collectors.toList());
    }

    public HotelLiteDto mapToHotelLiteDto(HotelEntityLite entity){
        return new HotelLiteDto(
                entity.getDestinationLocation()
        );
    }

    public HotelEntityLite mapToHotelLiteEntity(HotelLiteDto dto){
        return new HotelEntityLite(
                dto.getDestinationLocation()
        );
    }

    public HotelFilters mapToHotelFiltersEntity(HotelFiltersDto dto){
        return new HotelFilters(
                dto.getRating(),
                dto.getStars(),
                dto.getPriceMoreThan(),
                dto.getPriceLessThan()
        );
    }
}
