package com.orderplatform.orderservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.orderplatform.orderservice.events.UserCreatedEvent;
import com.orderplatform.orderservice.model.UserProfile;
import com.orderplatform.orderservice.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileListener {

    private final UserProfileRepository repo;

    @KafkaListener(topics = "user-created", groupId = "order-service")
    public void handleUserCreated(UserCreatedEvent event) {
        UserProfile p = UserProfile.builder()
                .id(event.getUserId())
                .email(event.getEmail())
                .name(event.getName())
                .build();

        repo.save(p);
        System.out.println("User profile synced to order-service: " + event.getEmail());
    }
}