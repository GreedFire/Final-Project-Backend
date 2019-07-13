package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.flight.location.FlightLocationDto;
import com.kodilla.backend.domain.entity.flight.location.FlightLocationEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightMapper {

    public List<FlightLocationEntity> mapToLocationEntityList(List<FlightLocationDto> locationDtoList, String writedLocation) {
        return locationDtoList.stream()
                .map(location -> new FlightLocationEntity(
                        location.getPlaceId(),
                        location.getPlaceName(),
                        location.getCountryName(),
                        writedLocation
                )).collect(Collectors.toList());
    }

    public List<FlightLocationDto> mapToLocationDtoList(List<FlightLocationEntity> locationEntityList) {
        return locationEntityList.stream()
                .map(location -> new FlightLocationDto(
                        location.getPlaceId(),
                        location.getPlaceName(),
                        location.getCountryName()
                )).collect(Collectors.toList());
    }


}
