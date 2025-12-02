🚀 Order Processing System — Event-Driven Microservices with Kafka

A production-grade event-driven distributed system built with Spring Boot, Kafka, Docker, PostgreSQL, JWT Authentication, and Microservice Architecture.
This project simulates how real-world systems (Amazon, Flipkart, DoorDash) process user registrations, inventory updates, and order confirmations asynchronously.


🏗 System Architecture


              ┌───────────────────┐
              │   Auth Service    │
              │  (JWT, PostgreSQL)│
              └───────┬───────────┘
                      │
      Register/Login  │
                      ▼
        ┌──────────────────────────┐
        │      Order Service       │
        │ (Create Order + Produce  │
        │      order.created)      │
        └───────────┬──────────────┘
                    │  Kafka Topic: order-events
                    ▼
        ┌──────────────────────────┐
        │    Inventory Service     │
        │ Validates stock & sends  │
        │  inventory.events        │
        └──────────┬───────────────┘
                   │  Kafka Topic: inventory-events
                   ▼
        ┌──────────────────────────┐
        │    Order Service (Update)│
        │ Updates status & triggers│
        │  notification.events     │
        └──────────┬───────────────┘
                   │  Kafka Topic: notification-events
                   ▼
       ┌────────────────────────────┐
       │    Notification Service    │
       │  Simulates Email/SMS       │
       └────────────────────────────┘




  📦 Microservices Included
          Service	              Responsibilities	                                            Port
          Auth Service	        User registration, login, JWT	                                8081
          Order Service	        Order creation, status management, Kafka producer/consumer	  8082
          Inventory             Service	Inventory validation, Kafka consumer/producer	        8083
          Notification          Service	Consumes events & simulates sending notifications	    8084
          PostgreSQL	          DB for Auth + Order Service	5432
          Kafka + Zookeeper	    Event backbone	9092 / 2181


🔑 Tech Stack

  Backend
  
  Java 17
    Spring Boot (3.5.x)
    Spring Web
    Spring Security (JWT)
    Spring Data JPA
    Spring Kafka
  
  Infrastructure
    Docker & Docker Compose
    Kafka + Zookeeper
    PostgreSQL
    HikariCP
  
  Other Tools
    Swagger/OpenAPI
    Lombok
    Postman Collection (for API testing)


⚙️ How to Run the Project (Docker)

1️⃣ Clone repository
        git clone https://github.com/<your-username>/order-processing-system.git
        cd order-processing-system

2️⃣ Run all services
        docker-compose up --build

3️⃣ Access Services
    Service	URL
      Auth Swagger UI	http://localhost:8081/swagger-ui.html
      Order Swagger UI	http://localhost:8082/swagger-ui.html

Kafka runs internally on:  kafka:9092


🔐 JWT Authentication Flow

  1. Register user
        POST /auth/register
  
  2. Login
        POST /auth/login
        → Returns JWT token
     
  3. Use token in Order API calls
      Headers:
         Authorization: Bearer <token>

🛒 Order Processing Flow (Kafka)

      1. Order Created
          Order Service → order-events
          
      2. Inventory Service validates stock
            Reads from order-events
            Responds via inventory-events
            
      3. Order Service updates status
            Updates DB
            Publishes notification-events
            
      4. Notification Service receives update
            Logs message:
            📩 Notification sent to user U123: Your order is confirmed!


📁 Kafka Topics

  Topic                       Name	                  Used By	Purpose
  
  order-events	              Order → Inventory	      When order is created
  inventory-events	          Inventory → Order	      Stock confirmation or rejection
  notification-events	        Order → Notification	  Final status message to user









