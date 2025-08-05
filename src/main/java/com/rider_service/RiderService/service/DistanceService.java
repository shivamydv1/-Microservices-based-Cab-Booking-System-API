package com.rider_service.RiderService.service;



import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@Service
public class DistanceService {
    public double getDistanceInKm(String source, String destination) {
    if (source.equalsIgnoreCase(destination)) {
        return 1.0;
    }

        if (source.contains("Sector") && destination.contains("Railway")) {
        return 8.5;
    }

        return 15.0 + new Random().nextInt(10){

}
    }

