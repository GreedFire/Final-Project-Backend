package com.kodilla.backend.controller;

import com.kodilla.backend.domain.dto.DeviceDto;
import com.kodilla.backend.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/devices")
public class DeviceController {

    @Autowired
    private DeviceService service;

    @PostMapping
    public void saveDeviceInfo(@RequestBody DeviceDto deviceDto){
        service.saveDevice(deviceDto);
    }
}
