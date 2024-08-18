package com.example.db.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_GATEWAY = "gateway";
    public static final String ROUTING_KEY_RATES_REGISTERED = "rates-registered";
    public static final String QUEUE_RATE_REGISTER = "rate-register";

    public static final String EXCHANGE_REQUEST_STATISTICS = "request-statistics";
    public static final String ROUTING_KEY_REQUEST_REGISTERED = "request-registered";
    public static final String QUEUE_REQUEST_REGISTER = "request-registered";

    @Bean
    public TopicExchange exchangeGateway() {
        return new TopicExchange(EXCHANGE_GATEWAY);
    }

    @Bean
    public Queue queueRateRegister() {
        return new Queue(QUEUE_RATE_REGISTER, false);
    }

    @Bean
    public Binding bindingGateway(Queue queueRateRegister, TopicExchange exchangeGateway) {
        return BindingBuilder.bind(queueRateRegister).to(exchangeGateway).with(ROUTING_KEY_RATES_REGISTERED);
    }

    @Bean
    public TopicExchange exchangeRequestStatistics() {
        return new TopicExchange(EXCHANGE_REQUEST_STATISTICS);
    }

    @Bean
    public Queue queueRequestRegister() {
        return new Queue(QUEUE_REQUEST_REGISTER, false);
    }

    @Bean
    public Binding bindingRequestStatistics(Queue queueRequestRegister, TopicExchange exchangeRequestStatistics) {
        return BindingBuilder.bind(queueRequestRegister).to(exchangeRequestStatistics).with(ROUTING_KEY_REQUEST_REGISTERED);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
