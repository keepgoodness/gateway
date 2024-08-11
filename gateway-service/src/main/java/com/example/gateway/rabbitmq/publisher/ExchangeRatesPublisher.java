package com.example.gateway.rabbitmq.publisher;

import com.example.gateway.configuration.RabbitMQConfig;
import com.example.gateway.util.MessagesInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRatesPublisher {
    private final AmqpTemplate amqpTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRatesPublisher.class);
    public ExchangeRatesPublisher(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(Object message){
        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_RATES_REGISTERED, message);
        LOGGER.info(String.format(MessagesInfo.MESSAGE_SENT, message));
    }
}
