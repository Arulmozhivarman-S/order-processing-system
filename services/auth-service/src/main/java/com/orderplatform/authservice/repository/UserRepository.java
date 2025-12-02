package com.orderplatform.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderplatform.authservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
