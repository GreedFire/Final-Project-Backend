package com.kodilla.backend.domain.entity.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQuery(
        name = "HotelEntityLite.retrieveMostInterestedLocation",
        query = "SELECT DESTINATION_LOCATION FROM (SELECT DESTINATION_LOCATION, COUNT(DESTINATION_LOCATION) AS NUMBER_OF_SEARCH FROM HOTEL_RESPONSES GROUP BY DESTINATION_LOCATION) ORDER BY NUMBER_OF_SEARCH DESC LIMIT 1",
        resultClass = HotelEntityLite.class
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOST_INTERESTED_HOTELS")
public class HotelEntityLite {

    @Id
    private String destinationLocation;
}
