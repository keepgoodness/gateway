package com.example.redis.listeners;

import com.example.redis.configuration.RabbitMQConfig;
import com.example.redis.model.hash.ExchangeRate;
import com.example.redis.model.response.FixerResponse;
import com.example.redis.repository.ExchangeRateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListeners {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQListeners.class);
    private final ExchangeRateRepository exchangeRateRepository;
    private final ObjectMapper mapper;
    private final RedisTemplate<String, Object> redisTemplate;

    public RabbitMQListeners(ExchangeRateRepository exchangeRateRepository,
                             ObjectMapper mapper, RedisTemplate<String, Object> redisTemplate) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.mapper = mapper;
        this.redisTemplate = redisTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.SUCCESS_QUEUE_NAME)
    public void handleSuccessMessage(String responseStr) {
        try {
            FixerResponse response = mapper.readValue(responseStr, FixerResponse.class);
            if (response != null && response.getSuccess()) {
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setTimestamp(response.getTimestamp());
                exchangeRate.setBase(response.getBase());
                exchangeRate.setDate(response.getDate());
                exchangeRate.setRates(response.getRates());
                exchangeRateRepository.save(exchangeRate);
                redisTemplate.opsForZSet().add("currency_rate", exchangeRate.getId(), exchangeRate.getTimestamp());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @RabbitListener(queues = RabbitMQConfig.ERROR_QUEUE_NAME)
    public void handleErrorMessage(String message) {
        LOGGER.error(message);
    }
}
