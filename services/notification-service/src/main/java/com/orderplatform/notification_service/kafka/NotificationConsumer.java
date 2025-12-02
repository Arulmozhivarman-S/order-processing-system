package com.orderplatform.notification_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.orderplatform.notification_service.model.NotificationEvent;

@Service
public class NotificationConsumer {
    
        @KafkaListener(topics = "notification-events", groupId = "notification-service")
        public void consume(NotificationEvent  event){
            System.out.println("📩 Notification sent to user " + event.getUserId() + ": " + event.getMessage());
        }

}
