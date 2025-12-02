package com.orderplatform.inventoryservice.service;

import com.orderplatform.inventoryservice.dto.OrderEvent;
import com.orderplatform.inventoryservice.kafka.InventoryEventProducer;
import com.orderplatform.inventoryservice.model.Inventory;
import com.orderplatform.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repo;
    private final InventoryEventProducer producer;

    public void processOrder(OrderEvent event) {

        Inventory inv = repo.findById(event.getProductId())
                .orElse(null);

        if (inv != null && inv.getStock() >= event.getQuantity()) {

            // deduct stock
            inv.setStock(inv.getStock() - event.getQuantity());
            repo.save(inv);

            // send success event
            producer.sendInventoryStatus(event.getOrderId(), "CONFIRMED");
        } else {
            // send failure
            producer.sendInventoryStatus(event.getOrderId(), "FAILED");
        }
    }
}
