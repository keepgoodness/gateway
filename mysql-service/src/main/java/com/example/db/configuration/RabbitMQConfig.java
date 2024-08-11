package com.example.db.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_GATEWAY = "gateway";
    public static final String ROUTING_KEY_RATES_REGISTERED = "rates-registered";
    public static final String QUEUE_RATE_REGISTER = "rate-register";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_GATEWAY);
    }

    @Bean
    public Queue queueSuccess() {
        return new Queue(QUEUE_RATE_REGISTER, false);
    }

    @Bean
    public Binding succesBinding(Queue queueSuccess, TopicExchange exchange) {
        return BindingBuilder.bind(queueSuccess).to(exchange).with(ROUTING_KEY_RATES_REGISTERED);
    }
   /* @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter()); // Use Jackson for JSON conversion
        return factory;
    }*/
}
