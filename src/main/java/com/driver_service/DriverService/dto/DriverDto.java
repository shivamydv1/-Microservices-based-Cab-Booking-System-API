package com.driver_service.DriverService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String vehicleNumber;
    private String licenseNumber;
    private String currentLocation;
    private boolean available;
}
