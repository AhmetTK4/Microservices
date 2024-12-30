package com.atk.notificationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String WAITING_QUEUE = "waitingQueue";
    public static final String PROCESSING_QUEUE = "processingQueue";
    public static final String DEAD_LETTER_QUEUE = "deadLetterQueue";

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public Queue waitingQueue() {
        return QueueBuilder.durable(WAITING_QUEUE)
                .withArgument("x-message-ttl", 300000) // 5 dakika TTL
                .withArgument("x-dead-letter-exchange", "") // Varsayılan exchange (direct exchange)
                .withArgument("x-dead-letter-routing-key", PROCESSING_QUEUE) // Mesajın taşınacağı routing key
                .build();
    }




    @Bean
    public Queue processingQueue() {
        return QueueBuilder.durable(PROCESSING_QUEUE).build();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setPrefetchCount(10);
        factory.setAdviceChain(RetryInterceptorBuilder.stateless()
                .maxAttempts(3)
                .recoverer(new org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer())
                .build());
        return factory;
    }
}
