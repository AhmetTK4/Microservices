package com.atk.notificationservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NotificationEntity {

    @Id
    private String orderId; // UUID olarak gelen string tipi

    private String customerName;
    private String status;
    private String messageContent;

    // Getters ve Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
