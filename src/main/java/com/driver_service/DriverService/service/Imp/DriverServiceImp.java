package com.driver_service.DriverService.service.Imp;

import com.driver_service.DriverService.dto.DriverDto;
import com.driver_service.DriverService.entity.Driver;
import com.driver_service.DriverService.repository.DriverRepository;
import com.driver_service.DriverService.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverServiceImp implements DriverService {

    private final DriverRepository driverRepository;

    private DriverDto mapToDTO(Driver driver) {
        return DriverDto.builder()
                .id(driver.getId())
                .name(driver.getName())
                .email(driver.getEmail())
                .phone(driver.getPhone())
                .vehicleNumber(driver.getVehicleNumber())
                .licenseNumber(driver.getLicenseNumber())
                .currentLocation(driver.getCurrentLocation())
                .available(driver.isAvailable())
                .build();
    }

    @Override
    public DriverDto registerDriver(DriverDto dto) {
        Driver driver = Driver.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .vehicleNumber(dto.getVehicleNumber())
                .licenseNumber(dto.getLicenseNumber())
                .available(true)
                .currentLocation(dto.getCurrentLocation())
                .available(dto.isAvailable())
                .build();

        driver = driverRepository.save(driver);

        return mapToDTO(driver);
    }

    @Override
    public DriverDto getDriverById(String id) {
        Driver driver = driverRepository.findById(id).orElseThrow();
        return mapToDTO(driver);
    }

    @Override
    public List<DriverDto> getAllDrivers() {
        return driverRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<DriverDto> getAvailableDrivers() {
        return driverRepository.findAllByAvailableTrue().stream()
                .map(this::mapToDTO)
                .toList();
    }




    @Override
    public void deleteDriver(String id) {
        driverRepository.deleteById(id);
    }
}
