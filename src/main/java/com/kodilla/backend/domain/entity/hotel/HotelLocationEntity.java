package com.kodilla.backend.domain.entity.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HOTELS_LOCATIONS")
public class HotelLocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int cityId;

    private String writedCity;

    public HotelLocationEntity(int cityId, String writedCity) {
        this.cityId = cityId;
        this.writedCity = writedCity;
    }
}
