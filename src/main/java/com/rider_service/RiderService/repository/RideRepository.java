package com.rider_service.RiderService.repository;

import com.rider_service.RiderService.entity.Ride;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface RideRepository extends MongoRepository<Ride , String> {

    List<Ride> findByUserId(String userId);
    List<Ride> findByDriverId(String driverId);

    Optional<Ride> findTopByUserIdOrderByCreatedAtDesc(String userId);
}
