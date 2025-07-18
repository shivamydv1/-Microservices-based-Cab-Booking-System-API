package com.driver_service.DriverService.service;

import com.driver_service.DriverService.dto.DriverDto;

import java.util.List;

public interface DriverService {
    DriverDto registerDriver(DriverDto driverDto);
    DriverDto getDriverById(String id);
    List<DriverDto> getAllDrivers();
    List<DriverDto> getAvailableDrivers();
    void deleteDriver(String id);
}
