package com.orderplatform.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.orderplatform.authservice.config.JwtService;
import com.orderplatform.authservice.dto.LoginRequest;
import com.orderplatform.authservice.dto.RegisterRequest;
import com.orderplatform.authservice.events.UserCreatedEvent;
import com.orderplatform.authservice.model.User;
import com.orderplatform.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
   
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final KafkaTemplate<String, Object> kafkaTemplate;


    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();

        userRepository.save(user);
        
        UserCreatedEvent event = new UserCreatedEvent(
            user.getId(),
            user.getEmail()
        );

        kafkaTemplate.send("user-created", event);


        return "Registered Successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtService.generateToken(user.getId(), user.getEmail());
    }


}
