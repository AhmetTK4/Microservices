package com.atk.orderservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RabbitMqTest {

  /*  @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue orderQueue;

    @Test
    void testSendMessage() {
        rabbitTemplate.convertAndSend(orderQueue.getName(), "Test message");
        Assertions.assertEquals("Test message", rabbitTemplate.receiveAndConvert(orderQueue.getName()));
    }*/
}
