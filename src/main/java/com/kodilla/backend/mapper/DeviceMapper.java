package com.kodilla.backend.mapper;

import com.kodilla.backend.domain.dto.DeviceDto;
import com.kodilla.backend.domain.entity.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {
    public Device mapToEntity(DeviceDto dto){
        return new Device(
                dto.getIpAddress(),
                dto.getHostName(),
                dto.getOS()
        );
    }
}
