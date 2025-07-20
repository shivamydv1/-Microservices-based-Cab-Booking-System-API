package com.rider_service.RiderService.feign;

import com.rider_service.RiderService.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    UserDto getUserById(@PathVariable("id") String id);
}
