package com.kodilla.backend.domain.entity.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private HotelSetEntity[] hotels;
}
