package com.atk.notificationservice.service;

import com.atk.notificationservice.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleOrder(String message) {
        log.info("Notification received for order: {}", message);
        // Bildirim gönderme işlemi burada yapılabilir
    }


}
