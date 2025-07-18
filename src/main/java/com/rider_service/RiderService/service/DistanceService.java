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

//    @Value("${google.maps.key}")
//    private String apiKey;
//
    public double getDistanceInKm(String source, String destination) {
//        String url = String.format("https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s",
//                UriUtils.encodePath(source , StandardCharsets.UTF_8),
//                UriUtils.encodePath(destination , StandardCharsets.UTF_8),
//                apiKey
//                );
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//        org.json.JSONObject json = new org.json.JSONObject(response.getBody());
//
//        JSONArray rows = json.getJSONArray("rows");
//        org.json.JSONObject elements = new org.json.JSONObject(0).getJSONArray("elements").getJSONObject(0);
//        int distanceInMeters = elements.getJSONObject("distance").getInt("value");
//
//        return distanceInMeters/1000.0;
//    }

    if (source.equalsIgnoreCase(destination)) {
        return 1.0;
    }

        if (source.contains("Sector") && destination.contains("Railway")) {
        return 8.5;
    }

        return 15.0 + new Random().nextInt(10);


}
    }
