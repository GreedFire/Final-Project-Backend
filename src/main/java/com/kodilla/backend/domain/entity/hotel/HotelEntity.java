package com.kodilla.backend.domain.entity.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "HotelResponse")
public class HotelEntity {

    @Id
    private String searchId;

    private String currency;

    private String destinationLocation;

    private String shareURL;

    @OneToMany(targetEntity = HotelSetEntity.class, mappedBy = "hotelResponse", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HotelSetEntity> hotels;

    public HotelEntity(String searchId, String currency, String destinationLocation, String shareURL) {
        this.searchId = searchId;
        this.currency = currency;
        this.destinationLocation = destinationLocation;
        this.shareURL = shareURL;
    }
}
