package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.Device;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceRepoTest {

    @Autowired
    private DeviceRepo repo;

    @Test
    public void save() {
        //Given
        Device device = new Device("xxx", "xxx", "xxx");
        //When
        repo.save(device);
        long id = device.getId();
        Optional<Device> device2 = repo.findById(id);
        //Then
        Assert.assertTrue(device2.isPresent());
        //CleanUp
        repo.deleteById(id);

    }
}