package com.rider_service.RiderService.feign;

import com.rider_service.RiderService.dto.DriverDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "driver-service", url = "http://localhost:8082")
public interface DriverClient {
    @GetMapping("driver/{id}")
    DriverDto getDriverById(@PathVariable("id") String id);

    @GetMapping("/driver/available")  // Fixed typo in URL
    List<DriverDto> getAvailableDrivers();
}
