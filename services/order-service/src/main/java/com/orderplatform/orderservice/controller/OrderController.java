package com.orderplatform.orderservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderplatform.orderservice.dto.CreateOrderRequest;
import com.orderplatform.orderservice.model.Order;
import com.orderplatform.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public Order createOrder(
            @RequestHeader("user-id") String userId,
            @RequestBody CreateOrderRequest req) {
        return service.createOrder(userId, req);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrders(@PathVariable String userId) {
        return service.getUserOrders(userId);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return service.getOrder(id);
    }
}
