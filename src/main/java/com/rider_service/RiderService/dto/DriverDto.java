package com.rider_service.RiderService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverDto {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String vehicleNumber;
    private String currentLocation;
    private boolean available;
}
