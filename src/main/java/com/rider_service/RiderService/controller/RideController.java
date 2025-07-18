package com.rider_service.RiderService.controller;

import com.rider_service.RiderService.dto.RideDto;
import com.rider_service.RiderService.service.DistanceService;
import com.rider_service.RiderService.service.RideService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;
    private final DistanceService distanceService;


    @GetMapping("/fare")
    public ResponseEntity<Map<String, Object>> estimateFare(
            @RequestParam String source,
            @RequestParam String destination) {

        try {
            double distance = distanceService.getDistanceInKm(source, destination);
            double fare = 50 + distance * 10;

            Map<String, Object> response = new HashMap<>();
            response.put("source", source);
            response.put("destination", destination);
            response.put("distance", distance);
            response.put("estimatedFare", fare);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Could not calculate fare: " + e.getMessage()));
        }
    }


    @PostMapping("/book")
    public ResponseEntity<RideDto> bookRide(@RequestBody RideDto rideDto){
        return ResponseEntity.ok(rideService.bookRide(rideDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RideDto> getRideById(@PathVariable  String id){
        return ResponseEntity.ok(rideService.getRideById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RideDto>> getRidesByUser(@PathVariable String userId){
        return ResponseEntity.ok(rideService.getRidesByUser(userId));
    }

    @GetMapping("driver/{driverId}")
    public ResponseEntity<List<RideDto>> getRidesByDriver(@PathVariable String driverId){
        return ResponseEntity.ok(rideService.getRidesByDriver(driverId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RideDto> updateRideStatus(@PathVariable String id, @RequestParam String status){
        return ResponseEntity.ok(rideService.updateRideStaus(id, status));
    }
    @GetMapping("/latest/{userId}")
    public ResponseEntity<RideDto> getLatestRide(@PathVariable String userId) {
        RideDto latestRide = rideService.getLatestRideByUserId(userId);
        return ResponseEntity.ok(latestRide);
    }
}
