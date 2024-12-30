package com.atk.notificationservice.service;

import com.atk.notificationservice.config.RabbitMQConfig;
import com.atk.notificationservice.dto.OrderMessage;
import com.atk.notificationservice.entity.NotificationEntity;
import com.atk.notificationservice.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);
    private final NotificationRepository notificationRepository;

    public NotificationListener(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.WAITING_QUEUE)
    public void handleWaitingOrder(OrderMessage orderMessage) {
        log.info("Order is waiting in queue: {}", orderMessage.getOrderId());

        // Mesajı veritabanına WAITING olarak kaydediyoruz
        NotificationEntity entity = new NotificationEntity();
        entity.setOrderId(orderMessage.getOrderId());
        entity.setCustomerName(orderMessage.getCustomerName());
        entity.setStatus("WAITING");
        entity.setMessageContent("Order is waiting in the queue.");

        notificationRepository.save(entity);

        log.info("Order saved as WAITING: {}", orderMessage.getOrderId());
    }

    @RabbitListener(queues = RabbitMQConfig.PROCESSING_QUEUE)
    public void handleProcessingOrder(OrderMessage orderMessage) {
        log.info("Processing order: {}", orderMessage.getOrderId());

        NotificationEntity entity = notificationRepository.findById(Long.parseLong(orderMessage.getOrderId()))
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderMessage.getOrderId()));

        entity.setStatus("SUCCESS");
        entity.setMessageContent("Order successfully processed.");

        notificationRepository.save(entity);

        log.info("Order updated to SUCCESS: {}", orderMessage.getOrderId());
    }

    @RabbitListener(queues = RabbitMQConfig.DEAD_LETTER_QUEUE)
    public void handleDeadLetter(String message) {
        log.warn("Dead letter received: {}", message);
    }
}
