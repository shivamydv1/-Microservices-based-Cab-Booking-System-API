package com.rider_service.RiderService.service;

import com.rider_service.RiderService.dto.RideDto;

import java.util.List;

public interface RideService {
    RideDto bookRide(RideDto rideDto);
    RideDto getRideById(String id);
    List<RideDto> getRidesByUser(String userId);
    List<RideDto> getRidesByDriver(String driverId);
    RideDto updateRideStaus(String id, String status);
    RideDto getLatestRideByUserId(String userId);

}
