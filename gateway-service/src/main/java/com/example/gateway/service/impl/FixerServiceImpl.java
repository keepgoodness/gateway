package com.example.gateway.service.impl;


import com.example.gateway.configuration.RabbitMQConfig;
import com.example.gateway.client.FixerClient;
import com.example.gateway.model.response.FixerResponse;
import com.example.gateway.rabbitmq.publisher.ExchangeRatesPublisher;
import com.example.gateway.service.FixerService;
import com.example.gateway.util.MessagesInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class FixerServiceImpl implements FixerService {
    private final FixerClient fixerClient;
    private final ExchangeRatesPublisher ratesPublisher;
    private static final Logger LOGGER = LoggerFactory.getLogger(FixerServiceImpl.class);

    public FixerServiceImpl(FixerClient fixerClient, ExchangeRatesPublisher ratesPublisher) {
        this.fixerClient = fixerClient;
        this.ratesPublisher = ratesPublisher;
    }

    @Async
    @Scheduled(fixedRateString = "${rates.fixedRate}")
    @Retryable(retryFor = {Exception.class},
            maxAttemptsExpression = "${retray.maxAttemps}",
            backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
    public void loadRates() {
        FixerResponse response = fixerClient.getLatestCurrencyRates();
        if (response != null) {
            if (response.getSuccess()) {
                ratesPublisher.sendMessage(response);
            } else {
                LOGGER.error(String.format(MessagesInfo.FIXER_LOAD_RATES_FAILED,response));
            }
        }
    }

    @Recover
    public void recover(Exception exception) {
        LOGGER.error(exception.getMessage());
    }

}
