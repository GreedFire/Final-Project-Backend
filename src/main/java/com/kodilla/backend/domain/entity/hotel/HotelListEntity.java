package com.kodilla.backend.domain.entity.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NamedNativeQuery(
        name = "HotelListEntity.retrieveFilteredHotels",
        query = "SELECT * FROM HOTELS WHERE HOTEL_ENTITY_ID = :RESPONSEID AND USER_RATING >= :RATING AND STARS >= :STARS AND PRICE BETWEEN :PRICEMORE AND :PRICELESS",
        resultClass = HotelListEntity.class
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HOTELS")
public class HotelListEntity {

    @Id
    private String id;

    private Double userRating;

    private BigDecimal price;

    private int stars;

    private String name;

    private String phone;

    private String address;

    private String city;

    private String country;

    private String displayaddress;

    private String thumburl;

    @ManyToOne
    @JoinColumn(name = "Hotel_Entity_ID")
    private HotelEntity hotelEntity;

    public HotelListEntity(String id, Double userRating, BigDecimal price, int stars, String name, String phone, String address, String city, String country, String displayaddress, String thumburl) {
        this.id = id;
        this.userRating = userRating;
        this.price = price;
        this.stars = stars;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.country = country;
        this.displayaddress = displayaddress;
        this.thumburl = thumburl;
    }
}
