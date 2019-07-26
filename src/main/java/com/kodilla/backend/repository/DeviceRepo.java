package com.kodilla.backend.repository;

import com.kodilla.backend.domain.entity.Device;
import com.kodilla.backend.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DeviceRepo extends CrudRepository<Device, Long> {
    Device save(Device device);
}
