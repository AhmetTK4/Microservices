package com.atk.orderservice.controller;

import com.atk.orderservice.config.RabbitMQConfig;
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
    public String createOrder(@RequestParam String order) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, order);
        return "Order sent: " + order;
    }

    @GetMapping("/")
    public String getOrders() {
        return "UP!";
    }
}
