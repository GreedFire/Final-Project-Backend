package com.kodilla.backend.domain.entity.hotel;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "HotelEntityLite.retrieveMostSearchedLocation",
                query = "SELECT DESTINATION_LOCATION FROM (SELECT DESTINATION_LOCATION, COUNT(DESTINATION_LOCATION) AS NUMBER_OF_SEARCH FROM HOTEL_RESPONSES GROUP BY DESTINATION_LOCATION) ORDER BY NUMBER_OF_SEARCH DESC LIMIT 1",
                resultClass = HotelEntityLite.class
        ),
        @NamedNativeQuery(
                name = "HotelEntityLite.saveMostSearchedLocation",
                query = "REPLACE INTO MOST_SEARCHED_LOCATIONS (SELECT DESTINATION_LOCATION FROM (SELECT DESTINATION_LOCATION, COUNT(DESTINATION_LOCATION) AS NUMBER_OF_SEARCH FROM HOTEL_RESPONSES GROUP BY DESTINATION_LOCATION) ORDER BY NUMBER_OF_SEARCH DESC LIMIT 1)",
                resultClass = HotelEntityLite.class
        )
})

@Data
@NoArgsConstructor
@Entity
@Table(name = "MOST_SEARCHED_LOCATIONS")
public class HotelEntityLite {

    @Id
    private String destinationLocation;

    public HotelEntityLite(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }
}
