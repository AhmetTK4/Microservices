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
        rabbitTemplate.convertAndSend("order-exchange", "order.routing.key", orderMessage);
        return "Order sent: " + orderMessage.getOrderId();
    }


    @GetMapping("/")
    public String getOrders() {
        return "UP!";
    }
}
