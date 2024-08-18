package com.example.db.publisher;

import com.example.db.configuration.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class StatisticsPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsPublisher.class);
    private final AmqpTemplate amqpTemplate;
    public StatisticsPublisher(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(Object message){
        amqpTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_REQUEST_STATISTICS,
                RabbitMQConfig.ROUTING_KEY_REQUEST_REGISTERED,
                message);
        LOGGER.info(String.format("Message send -> %s", message));
    }
}
