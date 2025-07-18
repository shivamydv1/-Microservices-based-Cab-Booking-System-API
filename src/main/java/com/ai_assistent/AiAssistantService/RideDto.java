package com.ai_assistent.AiAssistantService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RideDto {
    private String id;
    private String userId;
    private String driverId;
    private String driverName;
    private String driverPhone;
    private String driverVehicleNumber;
    private String source;
    private String destination;
    private double fare;
    private String status;
}
