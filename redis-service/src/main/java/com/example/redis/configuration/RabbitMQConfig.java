package com.example.redis.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "fixer-exchange";
    public static final String ROUTING_KEY_SUCCESS = "success";
    public static final String ROUTING_KEY_ERROR = "error";

    public static final String SUCCESS_QUEUE_NAME = "redis-success-queue";
    public static final String ERROR_QUEUE_NAME = "redis-error-queue";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queueSuccess() {
        return new Queue(SUCCESS_QUEUE_NAME, false);
    }

    @Bean
    public Queue queueError(){
        return new Queue(ERROR_QUEUE_NAME, false);
    }

    @Bean
    public Binding succesBinding(Queue queueSuccess, TopicExchange exchange) {
        return BindingBuilder.bind(queueSuccess).to(exchange).with(ROUTING_KEY_SUCCESS);
    }
    @Bean
    public Binding errorBinding(Queue queueError, TopicExchange exchange) {
        return BindingBuilder.bind(queueError).to(exchange).with(ROUTING_KEY_ERROR);
    }

//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter()); // Use Jackson for JSON conversion
//        return factory;
//    }
}
