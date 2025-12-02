package com.orderplatform.inventoryservice.kafka;

import com.orderplatform.inventoryservice.dto.OrderEvent;
import com.orderplatform.inventoryservice.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    private final InventoryService service;

    public OrderEventConsumer(InventoryService service) {
        this.service = service;
    }

    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consumeOrderEvent(OrderEvent event) {
        service.processOrder(event);
    }
}
