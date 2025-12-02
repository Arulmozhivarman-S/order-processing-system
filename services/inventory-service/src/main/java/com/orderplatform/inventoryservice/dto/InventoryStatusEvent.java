package com.orderplatform.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryStatusEvent {
    private String orderId;
    private String status;
}
