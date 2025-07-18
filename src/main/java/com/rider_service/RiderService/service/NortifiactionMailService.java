package com.rider_service.RiderService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NortifiactionMailService {

    private final JavaMailSender javaMailSender;


    public void sendRideConfirmationEmail(String toEmail, String userName, String source, String destination, double fare) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shvmskyadav@gmail.com");   // ✅ Your Gmail
        message.setTo(toEmail);
        message.setSubject("Cab Ride Booking Confirmation");

        String text = "Hi " + userName + ",\n\n"
                + "Your cab ride has been booked successfully.\n\n"
                + "From: " + source + "\n"
                + "To: " + destination + "\n"
                + "Amount Paid: ₹" + fare + "\n\n"
                + "Thank you for riding with ShivamCab! 🚖\n\n"
                + "Regards,\nShivamCab Booking System";

        message.setText(text);

        javaMailSender.send(message);

    }
}
