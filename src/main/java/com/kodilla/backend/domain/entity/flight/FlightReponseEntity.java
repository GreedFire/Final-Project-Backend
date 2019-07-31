package com.kodilla.backend.domain.entity.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "FLIGHTS")
public class FlightReponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime departureDate;

    private String origin;

    private String destination;

    private LocalDate searchDate;

    @OneToMany(targetEntity = FlightCarriersEntity.class, mappedBy = "flightReponseEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FlightCarriersEntity> carriers;

    public FlightReponseEntity(LocalDateTime departureDate, String origin, String destination) {
        this.departureDate = departureDate;
        this.origin = origin;
        this.destination = destination;
        this.searchDate = LocalDate.now();
        this.carriers = new ArrayList<>();
    }
}
