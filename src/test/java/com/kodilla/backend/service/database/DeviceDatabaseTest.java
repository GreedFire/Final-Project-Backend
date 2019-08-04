package com.kodilla.backend.service.database;

import com.kodilla.backend.domain.entity.Device;
import com.kodilla.backend.repository.DeviceRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceDatabaseTest {

    @MockBean
    private DeviceDatabase database;

    @MockBean
    private DeviceRepo deviceRepo;

    @Test
    public void save() {
        //Given
        Device device = new Device("1", "2", "3");
        when(deviceRepo.save(device)).thenReturn(device);
        //When
        database.save(device);
        //Then
        Mockito.verify(database, times(1)).save(device);

    }
}