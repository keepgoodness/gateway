package com.example.db.listeners;

import com.example.db.configuration.RabbitMQConfig;
import com.example.db.model.entity.Rate;
import com.example.db.model.response.FixerResponse;
import com.example.db.repository.RateRepository;
import com.example.db.helper.CacheClearHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GatewayServiceListeners {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayServiceListeners.class);
    private final RateRepository rateRepository;
    private final ObjectMapper mapper;
    private final CacheClearHelper cacheClearHelper;

    public GatewayServiceListeners(RateRepository rateRepository,
                                   ObjectMapper mapper, CacheClearHelper cacheClearHelper) {
        this.rateRepository = rateRepository;
        this.mapper = mapper;
        this.cacheClearHelper = cacheClearHelper;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_RATE_REGISTER)
    public void handleRateRegisterMessage(String responseStr) {
        try {
            FixerResponse response = mapper.readValue(responseStr, FixerResponse.class);
            List<Rate> rates = response.getRates().entrySet().stream()
                    .map(r -> {
                        Rate rate = new Rate();
                        rate.setBaseCurrency(response.getBase());
                        rate.setCurrency(r.getKey());
                        rate.setRate(r.getValue());
                        rate.setTimestamp(Timestamp.from(Instant.ofEpochSecond(response.getTimestamp())));
                        return rate;
                    })
                    .collect(Collectors.toList());
            rateRepository.saveAll(rates);
            LOGGER.info("Rates loaded and saved in database successfully.");
            cacheClearHelper.clearCaches("rate", "rateList");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
