package com.orderplatform.inventoryservice.kafka;

import com.orderplatform.inventoryservice.dto.InventoryStatusEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventProducer {

    private final KafkaTemplate<String, Object> template;

    public InventoryEventProducer(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    public void sendInventoryStatus(String orderId, String status) {
        template.send("inventory-events", new InventoryStatusEvent(orderId, status));
    }
}
