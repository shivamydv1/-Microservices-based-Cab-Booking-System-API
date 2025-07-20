package com.ai_assistent.AiAssistantService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RiderService")
public interface RideClient {
    @GetMapping("/ride/latest/{userId}")
    RideDto getLatestRide(@PathVariable("userId") String userId);
}
