package com.driver_service.DriverService.repository;

import com.driver_service.DriverService.entity.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DriverRepository extends MongoRepository<Driver, String> {
    List<Driver> findAllByAvailableTrue();

}
