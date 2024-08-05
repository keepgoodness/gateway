package com.example.db.listeners;

import com.example.db.configuration.RabbitMQConfig;
import com.example.db.model.response.FixerResponse;
import com.example.db.model.entity.ExchangeRate;
import com.example.db.repository.ExchangeRateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListeners {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQListeners.class);
    private final ExchangeRateRepository exchangeRateRepository;
    private final ObjectMapper mapper;

    public RabbitMQListeners(ExchangeRateRepository exchangeRateRepository,
                             ObjectMapper mapper) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.mapper = mapper;
    }

    @RabbitListener(queues = RabbitMQConfig.SUCCESS_QUEUE_NAME)
    public void handleSuccessMessage(String responseStr) {
        try {
            FixerResponse response = mapper.readValue(responseStr, FixerResponse.class);
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setTimestamp(response.getTimestamp());
            exchangeRate.setBase(response.getBase());
            exchangeRate.setDate(response.getDate());
            exchangeRate.setRates(response.getRates());
            exchangeRateRepository.save(exchangeRate);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @RabbitListener(queues = RabbitMQConfig.ERROR_QUEUE_NAME)
    public void handleErrorMessage(String message) {
        LOGGER.error(message);
    }
}
