package com.orderplatform.orderservice.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private String itemName;
    private int quantity;
}
