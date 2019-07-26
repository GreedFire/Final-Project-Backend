package com.kodilla.backend.mapper.mappers;

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

    public DeviceDto mapToDto(Device entity){
        return new DeviceDto(
                entity.getIpAddress(),
                entity.getHostName(),
                entity.getOS()
        );
    }
}
