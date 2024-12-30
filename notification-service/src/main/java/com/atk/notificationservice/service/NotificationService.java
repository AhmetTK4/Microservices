package com.atk.notificationservice.service;

import com.atk.notificationservice.entity.NotificationEntity;
import com.atk.notificationservice.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveNotification(NotificationEntity notification) {
        repository.save(notification);
        log.info("Notification saved: {}", notification.getOrderId());
    }
}
