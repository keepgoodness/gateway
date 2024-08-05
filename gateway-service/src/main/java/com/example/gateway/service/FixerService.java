package com.example.gateway.service;


import com.example.gateway.configuration.RabbitMQConfig;
import com.example.gateway.feign.FixerFeignClient;
import com.example.gateway.model.response.FixerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class FixerService {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;
    private final FixerFeignClient fixerFeignClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(FixerService.class);

    public FixerService(AmqpTemplate amqpTemplate,
                        ObjectMapper objectMapper,
                        FixerFeignClient fixerFeignClient) {
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = objectMapper;
        this.fixerFeignClient = fixerFeignClient;
    }

    @Async
    @Scheduled(fixedRateString = "${rates.fixedRate}")
    @Retryable(retryFor = {Exception.class},
            maxAttemptsExpression = "${retray.maxAttemps}",
            backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    public void updateCurrencyRates() {
        FixerResponse response = fixerFeignClient.getLatestCurrencyRates();
        if (response != null) {
            String responseStr = convert(response);
            if (response.getSuccess()) {
                amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_SUCCESS, responseStr);
            } else {
                amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_ERROR, responseStr);
            }
        }
    }

    @Recover
    public void recover(Exception exception) {
        LOGGER.error(exception.getMessage());
    }

    private String convert(FixerResponse response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException ex) {
            LOGGER.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

}
