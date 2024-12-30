package com.atk.orderservice.controller;

import com.atk.orderservice.config.RabbitMQConfig;
import com.atk.orderservice.dto.OrderMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final RabbitTemplate rabbitTemplate;

    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderMessage orderMessage) {
        if ("WAITING".equalsIgnoreCase(orderMessage.getStatus())) {
            rabbitTemplate.convertAndSend("order-exchange", "waiting.routing.key", orderMessage);
            return "Order is waiting in the queue: " + orderMessage.getOrderId();
        } else if ("SUCCESS".equalsIgnoreCase(orderMessage.getStatus())) {
            rabbitTemplate.convertAndSend("order-exchange", "process.routing.key", orderMessage);
            return "Order processed immediately: " + orderMessage.getOrderId();
        } else {
            return "Invalid status. Only WAITING or SUCCESS are allowed.";
        }
    }

    @GetMapping("/")
    public String getOrders() {
        return "UP!";
    }
}
