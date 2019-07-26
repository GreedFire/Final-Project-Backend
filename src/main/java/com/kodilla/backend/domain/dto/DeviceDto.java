package com.kodilla.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceDto {
    private String ipAddress;
    private String hostName;
    private String OS;
}
