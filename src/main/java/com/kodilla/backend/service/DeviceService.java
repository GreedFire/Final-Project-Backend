package com.kodilla.backend.service;

import com.kodilla.backend.domain.dto.DeviceDto;
import com.kodilla.backend.mapper.DeviceMapper;
import com.kodilla.backend.service.database.DeviceDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceDatabase database;

    @Autowired
    private DeviceMapper mapper;

    public void saveDevice(DeviceDto deviceDto) {
        database.save(mapper.mapToEntity(deviceDto));
    }

}
