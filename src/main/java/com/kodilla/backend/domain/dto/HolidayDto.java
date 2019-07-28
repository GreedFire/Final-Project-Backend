package com.kodilla.backend.domain.dto;

import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.dto.hotel.HotelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HolidayDto {

    private List<HotelDto> hotels;
    private List<FlightDto> tripFlights;
    private List<FlightDto> returnFlight;


}
