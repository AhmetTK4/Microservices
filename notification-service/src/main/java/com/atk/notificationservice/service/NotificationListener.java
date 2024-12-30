package com.atk.notificationservice.service;

import com.atk.notificationservice.config.RabbitMQConfig;
import com.atk.notificationservice.dto.OrderMessage;
import com.atk.notificationservice.entity.NotificationEntity;
import com.atk.notificationservice.exception.SpecificException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);
    private final NotificationService notificationService;

    public NotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleOrder(OrderMessage orderMessage) {
        log.info("Processing order: {}", orderMessage.getOrderId());
        NotificationEntity entity = new NotificationEntity();
        entity.setOrderId(orderMessage.getOrderId());
        entity.setCustomerName(orderMessage.getCustomerName());
        entity.setStatus(orderMessage.getStatus());
        entity.setMessageContent("Message processed successfully.");

        notificationService.saveNotification(entity);
    }


    @RabbitListener(queues = "deadLetterQueue")
    public void handleDeadLetter(String message) {
        log.warn("Dead letter received: {}", message);
    }


    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleOrderWithRetry(OrderMessage orderMessage) {
        try {
            log.info("Processing order: {}", orderMessage.getOrderId());
            if ("ERROR".equals(orderMessage.getStatus())) {
                throw new SpecificException("Error processing message");
            }
            log.info("Order processed successfully: {}", orderMessage.getOrderId());
        } catch (SpecificException e) {
            log.error("Retry failed for message: {}", orderMessage.getOrderId(), e);
            throw e;
        }
    }






}
