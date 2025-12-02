package com.orderplatform.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.orderplatform.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByUserId(String userId);
} 
