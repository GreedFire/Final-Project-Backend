package com.kodilla.backend.domain.entity.flight.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "FLIGHT_LOCATIONS")
public class FlightLocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String placeId;

    private String placeName;

    private String countryName;

    private String writedLocation;

    public FlightLocationEntity(String placeId, String placeName, String countryName, String writedLocation) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.countryName = countryName;
        this.writedLocation = writedLocation;
    }
}
