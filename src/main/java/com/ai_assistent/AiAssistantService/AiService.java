package com.ai_assistent.AiAssistantService;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {

    private final RideClient rideClient;
    private final OllamaChatModel ollamaChatModel;

    public String generateSmartReply(String userId, String userPrompt) {
        RideDto ride = rideClient.getLatestRide(userId); // Fetch from rider-service

        String context = String.format(
                "Your current ride is from %s to %s.\nDriver: %s, Phone: %s, Vehicle: %s.\nFare: ₹%.2f\n\n",
                ride.getSource(),
                ride.getDestination(),
                ride.getDriverName(),
                ride.getDriverPhone(),
                ride.getDriverVehicleNumber(),
                ride.getFare()
        );

        String finalPrompt = context + "User Question: " + userPrompt;

        return ollamaChatModel.call(finalPrompt);
    }
}
