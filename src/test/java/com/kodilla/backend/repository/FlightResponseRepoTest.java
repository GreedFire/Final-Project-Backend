package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.flight.FlightCarriersEntity;
import com.kodilla.backend.domain.entity.flight.FlightReponseEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightResponseRepoTest {

    @Autowired
    private FlightResponseRepo repo;

    @Test
    public void save() {
        //Given
        LocalDateTime dateTime = LocalDateTime.now();
        FlightReponseEntity entity = new FlightReponseEntity(dateTime, "Berlin", "New York");
        //When
        repo.save(entity);
        long id = entity.getId();
        Optional<FlightReponseEntity> foundById = repo.findById(id);
        List<FlightReponseEntity> foundByDepartureDateAndOriginAndDestination = repo.findByDepartureDateAndOriginAndDestination(dateTime, "Berlin", "New York");
        LocalDate dateAfter = LocalDate.now().minusDays(1);
        List<FlightReponseEntity> foundAllBySearchDateAfter = repo.findAllBySearchDateAfter(dateAfter);
        //Then
        Assert.assertTrue(foundById.isPresent());
        Assert.assertTrue(foundByDepartureDateAndOriginAndDestination.size() > 0);
        Assert.assertTrue(foundAllBySearchDateAfter.size() > 0);
        //CleanUp
        repo.deleteById(id);
    }
}