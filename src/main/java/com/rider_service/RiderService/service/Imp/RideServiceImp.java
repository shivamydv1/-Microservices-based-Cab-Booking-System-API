package com.rider_service.RiderService.service.Imp;

import com.rider_service.RiderService.dto.DriverDto;
import com.rider_service.RiderService.dto.RideDto;
import com.rider_service.RiderService.dto.UserDto;
import com.rider_service.RiderService.entity.Ride;
import com.rider_service.RiderService.feign.DriverClient;
import com.rider_service.RiderService.feign.UserClient;
import com.rider_service.RiderService.repository.RideRepository;
import com.rider_service.RiderService.service.DistanceService;
import com.rider_service.RiderService.service.NortifiactionMailService;
import com.rider_service.RiderService.service.RideService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImp implements RideService {

    private final RideRepository rideRepository;
    private final DistanceService distanceService;
    private final DriverClient driverClient;
    private final UserClient userClient;
    private final NortifiactionMailService mailService;


    private RideDto mapToDto(Ride rider){
        return RideDto.builder()
                .id(rider.getId())
                .userId(rider.getUserId())
                .driverId(rider.getDriverId())
                .driverName(rider.getDriverName())
                .driverPhone(rider.getDriverPhone())
                .driverVehicleNumber(rider.getDriverVehicleNumber())
                .source(rider.getSource())
                .destination(rider.getDestination())
                .fare(rider.getFare())
                .status(rider.getStatus())
                .createdAt(rider.getCreatedAt())
                .updatedAt(rider.getUpdatedAt())

                .build();
    }


    @Override
    public RideDto bookRide(RideDto rideDto) {
        try {
            List<DriverDto> availableDrivers = driverClient.getAvailableDrivers();
            if (availableDrivers == null || availableDrivers.isEmpty()) {
                throw new RuntimeException("No available drivers at the moment");
            }

            DriverDto selectedDriver;
            if (rideDto.getDriverId() != null) {
                selectedDriver = availableDrivers.stream()
                        .filter(driver -> driver.getId().equals(rideDto.getDriverId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Selected driver is not available"));
            } else {
                // 🔁 Random driver assignment
                selectedDriver = availableDrivers.get(new Random().nextInt(availableDrivers.size()));
            }
            rideDto.setDriverId(selectedDriver.getId());
            rideDto.setDriverName(selectedDriver.getName());
            rideDto.setDriverPhone(selectedDriver.getPhone());
            rideDto.setDriverVehicleNumber(selectedDriver.getVehicleNumber());


//            rideDto.setDriverId(availableDrivers.get(0).getId());
//            rideDto.setDriverName(availableDrivers.get(0).getName());
//            rideDto.setDriverPhone(availableDrivers.get(0).getPhone());
//            rideDto.setDriverVehicleNumber(availableDrivers.get(0).getVehicleNumber());
            double distance = distanceService.getDistanceInKm(rideDto.getSource(), rideDto.getDestination());
            double fare = 50 + distance * 10;
            rideDto.setFare(fare);

            Ride ride = Ride.builder()
                    .userId(rideDto.getUserId())
                    .driverId(rideDto.getDriverId())
                    .driverName(rideDto.getDriverName())
                    .driverPhone(rideDto.getDriverPhone())
                    .driverVehicleNumber(rideDto.getDriverVehicleNumber())
                    .source(rideDto.getSource())
                    .destination(rideDto.getDestination())
                    .fare(fare)
                    .status("BOOKED")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Ride savedRide = rideRepository.save(ride);

            UserDto user = userClient.getUserById(rideDto.getUserId());

            mailService.sendRideConfirmationEmail(
                    user.getEmail(),                      // ✅ email from user-service
                    user.getName(),                       // ✅ name from user-service
                    savedRide.getSource(),
                    savedRide.getDestination(),
                    savedRide.getFare()
            );


            return mapToDto(savedRide);
        } catch (FeignException e) {
            throw new RuntimeException("Error communicating with driver service: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error booking ride: " + e.getMessage());
        }


    }

    @Override
    public RideDto getRideById(String id) {
        Ride rider = rideRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));
        return mapToDto(rider);
    }

    @Override
    public List<RideDto> getRidesByUser(String userId) {
        return rideRepository.findByUserId(userId).stream().map(this::mapToDto).toList();

    }

    @Override
    public List<RideDto> getRidesByDriver(String driverId) {
        return rideRepository.findByDriverId(driverId).stream().map(this::mapToDto).toList();
    }

    @Override
    public RideDto updateRideStaus(String id, String status) {
        Ride rider = rideRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));
    
        rider.setStatus(status);
        rider.setUpdatedAt(LocalDateTime.now());
        return mapToDto(rideRepository.save(rider));
    }


    @Override
    public RideDto getLatestRideByUserId(String userId) {
        Ride ride = rideRepository.findTopByUserIdOrderByCreatedAtDesc(userId)
                .orElseThrow(() -> new RuntimeException("No ride found for userId: " + userId));
        return mapToDto(ride);
    }

}
