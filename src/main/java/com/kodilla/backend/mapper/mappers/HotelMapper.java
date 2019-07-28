package com.kodilla.backend.mapper.mappers;

import com.kodilla.backend.domain.dto.HotelFiltersDto;
import com.kodilla.backend.domain.dto.hotel.HotelDto;
import com.kodilla.backend.domain.dto.hotel.HotelListDto;
import com.kodilla.backend.domain.dto.hotel.HotelLiteDto;
import com.kodilla.backend.domain.dto.hotel.HotelLocationDto;
import com.kodilla.backend.domain.entity.HotelFilters;
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

    public HotelEntity mapToHotelEntity(HotelDto hotelDto) {
        HotelEntity hotelResponseResult = new HotelEntity(
                hotelDto.getSearchId(),
                hotelDto.getCurrency(),
                hotelDto.getDestinationLocation(),
                hotelDto.getShareURL()
        );
        List<HotelListEntity> hotelListEntity = hotelDto.getHotels().stream()
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

    public List<HotelListDto> mapToHotelListDto(List<HotelListEntity> list) {
        return list.stream()
                .map(hotelset -> new HotelListDto(
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

    public HotelDto mapToHotelDto(HotelEntity hotelEntity) {
        HotelDto hotelDto = new HotelDto(
                hotelEntity.getSearchId(),
                hotelEntity.getCurrency(),
                hotelEntity.getDestinationLocation(),
                hotelEntity.getShareURL()
        );

        List<HotelListDto> hotelListDto = hotelEntity.getHotels().stream()
                .map(hotelset -> new HotelListDto(
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
        hotelDto.setHotels(hotelListDto);
        return hotelDto;
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
