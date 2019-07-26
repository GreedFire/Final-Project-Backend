package com.kodilla.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEVICES_INFO")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ipAddress;
    private String hostName;
    private String OS;

    public Device(String ipAddress, String hostName, String OS) {
        this.ipAddress = ipAddress;
        this.hostName = hostName;
        this.OS = OS;
    }
}
