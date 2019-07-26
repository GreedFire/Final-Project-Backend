package com.kodilla.backend.service.database;

import com.kodilla.backend.domain.entity.Device;
import com.kodilla.backend.repository.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceDatabase {
    @Autowired
    private DeviceRepo deviceRepo;

    public void save(Device device) {
        deviceRepo.save(device);
    }
}
