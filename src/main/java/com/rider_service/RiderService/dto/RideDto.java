package com.rider_service.RiderService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private String id;
    private String userId;
    private String driverId;
    private String driverName;
    private String driverPhone;
    private String driverVehicleNumber;
    private String source;
    private String destination;
    private Double fare;
    private String status; // BOOKED, ONGOING, COMPLETED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
