package com.kodilla.backend.domain.dto.holiday;

import com.kodilla.backend.domain.dto.flight.FlightDto;
import com.kodilla.backend.domain.dto.hotel.HotelListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HolidayDto {

    private List<HotelListDto> hotels;
    private List<FlightDto> tripFlights;
    private List<FlightDto> returnFlight;


}
