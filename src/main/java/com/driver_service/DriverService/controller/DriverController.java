package com.driver_service.DriverService.controller;

import com.driver_service.DriverService.dto.DriverDto;
import com.driver_service.DriverService.service.DriverService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<DriverDto> registerDriver(@RequestBody DriverDto driverDto) {
        return ResponseEntity.ok(driverService.registerDriver(driverDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable String id) {
        return ResponseEntity.ok(driverService.getDriverById(id));

    }

    @GetMapping
    public ResponseEntity<List<DriverDto>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @GetMapping("/available")
    public ResponseEntity<List<DriverDto>> getAvailableDrivers() {
        return ResponseEntity.ok(driverService.getAvailableDrivers());
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable String id) {
        driverService.deleteDriver(id);
        System.out.println("Driver with id: " + id + " has been deleted");
    }

}
