package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.flight.FlightCarriersDto;
import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.dto.flight.skyscanner.SkyscannerFlightReponseDto;
import com.kodilla.backend.domain.dto.flight.location.FlightLocationDto;
import com.kodilla.backend.domain.entity.flight.FlightCarriersEntity;
import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import com.kodilla.backend.domain.entity.flight.location.FlightLocationEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class FlightMapper {

    private final String BUSSINESS = "bussines";
    private final String ECONOMIC = "economic";
    private final String FIRST = "first";

    public List<FlightDto> mapToFlightDtoList(List<FlightReponseEntity> list){
        return list.stream().map(entity -> new FlightDto(
                        entity.getId(),
                        entity.getDepartureDate(),
                        entity.getOrigin(),
                        entity.getDestination(),
                        entity.getCarriers().stream().map(carrier -> new FlightCarriersDto(
                                carrier.getId(),
                                carrier.getCarriedId(),
                                carrier.getCarrierName(),
                                carrier.getPrice(),
                                carrier.getOutboundDate(),
                                carrier.getCarrierClass()
                        )).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public FlightDto mapToFlightDto(FlightReponseEntity entity){
        FlightDto result = new FlightDto(
                entity.getId(),
                entity.getDepartureDate(),
                entity.getOrigin(),
                entity.getDestination()
        );

        List<FlightCarriersDto> carriers = entity.getCarriers().stream()
                .map(carrier -> new FlightCarriersDto(
                        carrier.getId(),
                        carrier.getCarriedId(),
                        carrier.getCarrierName(),
                        carrier.getPrice(),
                        carrier.getOutboundDate(),
                        carrier.getCarrierClass()
                )).collect(Collectors.toList());

        result.setCarriers(carriers);
        return result;

    }

    private String generateCarrierClass(){
        Random generator = new Random();
        String result = "";
        switch(generator.nextInt(3)){
            case 0: result = ECONOMIC; break;
            case 1: result = BUSSINESS;break;
            case 2: result = FIRST; break;
        }
        return result;
    }

    public FlightReponseEntity mapToReponseEntity(SkyscannerFlightReponseDto skyscannerFlightReponseDto) {
        BigDecimal price = new BigDecimal(skyscannerFlightReponseDto.getQuotes().get(0).getPrice().intValue());
        Random generator = new Random();
        String dateText = skyscannerFlightReponseDto.getQuotes().get(0).getOutBoundLeg().getDepartureDate(); // "2019-09-01T00:00:00" format
        LocalDateTime date = LocalDateTime.parse(dateText);

        FlightReponseEntity reponseEntity = new FlightReponseEntity(
                date,
                skyscannerFlightReponseDto.getPlaces().get(1).getName(),
                skyscannerFlightReponseDto.getPlaces().get(0).getName()
        );

        List<FlightCarriersEntity> carriers = skyscannerFlightReponseDto.getCarriers().stream()
                .map(carrier ->
                        new FlightCarriersEntity(
                        carrier.getCarriedId(),
                        carrier.getCarrierName(),
                        price.add(BigDecimal.valueOf(generator.nextInt(11))),
                        date.plusHours(generator.nextInt(24)).plusMinutes(generator.nextInt(60)),
                        generateCarrierClass(), // NO RISING PRICE FOR CLASS FOR NOW
                        reponseEntity
                )).collect(Collectors.toList());

        reponseEntity.setCarriers(carriers);

        return reponseEntity;
    }

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
