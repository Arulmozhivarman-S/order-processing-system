package com.orderplatform.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orderplatform.orderservice.dto.CreateOrderRequest;
import com.orderplatform.orderservice.model.Order;
import com.orderplatform.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repo;

    public Order createOrder(String userId, CreateOrderRequest req) {

        Order order = Order.builder()
                .userId(userId)
                .itemName(req.getItemName())
                .quantity(req.getQuantity())
                .status("PENDING")
                .build();

        return repo.save(order);
    }

    public List<Order> getUserOrders(String userId) {
        return repo.findByUserId(userId);
    }

    public Order getOrder(String id) {
        return repo.findById(id).orElseThrow();
    }
}
