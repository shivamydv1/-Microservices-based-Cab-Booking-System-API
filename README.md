# -Microservices-based-Cab-Booking-System-API
A Cab Booking System built with Spring Boot Microservices architecture.   It includes user, driver, ride management, AI assistant, and fare estimation features.

A mini cab booking system based on **Spring Boot Microservices**, allowing users to book cabs, check fare, assign drivers, and get ride history. AI assistant and distance logic are also integrated.

Project Structure

api-gateway/
user-service/
driver-service/
rider-service/
ai-assistant-service/

## 🚀 Technologies Used

- Java 17
- Spring Boot (REST APIs)
- Spring Cloud Gateway (WebFlux)
- Eureka Discovery Service
- MongoDB
- Feign Client (inter-service calls)
- OpenRouteService API (distance)
- JavaMailSender (notifications)
- Ollama (AI Assistant)
- Maven

 AI Assistant Microservice
The ai-assistant-service is a microservice that allows users to interact with the cab booking platform through natural language prompts using a locally running Ollama AI model (like Mistral, LLaMA, etc.).

✨ Features
Accepts user prompts like:

"Show my last ride details"

"Check fare from Delhi to Gurgaon"

Returns intelligent responses using LLM

Integrates with other microservices (User, Ride, Driver) for contextual info

Built using Spring Boot and Ollama REST API

📦 Technologies Used
Spring Boot

Ollama (local LLM)

Feign Clients (optional for user/ride fetch)

REST APIs

## 📂 Microservices Overview

### 🔹 User Service
- Register and get users
- Fields: name, email

### 🔹 Driver Service
- Manage drivers
- Update driver availability
- Get available drivers

### 🔹 Rider Service
- Book ride with driver (auto or manual)
- Calculate fare using distance
- Ride status: BOOKED → COMPLETED
- Email notification on booking

### 🔹 AI Assistant
- Ask questions with:  
  `GET /ai/prompt?prompt=Hello`
  `GET /ai/query?userId"'&userPrompt=""`

### 🔹 API Gateway
- Routes traffic to services
- Example: `http://localhost:8080/user`

📬 Email Confirmation
On successful ride booking, a confirmation email is sent to the user's email address.

 Fare Logic
Fare is calculated as:

ini
Copy
Edit
fare = 50 + (distanceInKm * 10)

💡 How to Run
Start Eureka Discovery Server

Run:

user-service

driver-service

rider-service

ai-assistant-service

api-gateway

Use Postman or browser to access APIs via:
http://localhost:8080

🌐 Useful APIs
GET /user

GET /driver/available

POST /ride/book

GET /ride/user/{userId}

GET /ai/prompt?prompt=Hello

🧠 AI Assistant (Ollama)
Locally integrated Ollama model can respond to prompts via /ai/prompt endpoint.

Book Ride
![image alt](https://github.com/shivamydv1/-Microservices-based-Cab-Booking-System-API/blob/main/Screenshot%202025-07-19%20220054.png?raw=true)
