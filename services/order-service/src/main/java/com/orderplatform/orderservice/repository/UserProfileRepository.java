package com.orderplatform.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderplatform.orderservice.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    
} 
