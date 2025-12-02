package com.orderplatform.authservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderplatform.authservice.dto.LoginRequest;
import com.orderplatform.authservice.dto.RegisterRequest;
import com.orderplatform.authservice.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (Exception e) {
            e.printStackTrace();  // print full exception in logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(e.getMessage());
        }
    }


    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req){
        String token = authService.login(req);
        return ResponseEntity.ok(Map.of("token", token));
    }

}
