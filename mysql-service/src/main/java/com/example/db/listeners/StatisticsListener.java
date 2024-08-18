package com.example.db.listeners;

import com.example.db.configuration.RabbitMQConfig;
import com.example.db.model.request.GatewayRequestDTO;
import com.example.db.publisher.StatisticsPublisher;
import com.example.db.service.StatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StatisticsListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsPublisher.class);
    private final StatisticsService statisticsService;
    private final ObjectMapper mapper;
    public StatisticsListener(StatisticsService statisticsService, ObjectMapper mapper) {
        this.statisticsService = statisticsService;
        this.mapper = mapper;
    }
    @RabbitListener(queues = RabbitMQConfig.QUEUE_REQUEST_REGISTER)
    public void handleRequestStatisticsMessage(String message){
        LOGGER.info(String.format("Message received -> %s", message));
        try {
            GatewayRequestDTO response = mapper.readValue(message, GatewayRequestDTO.class);
            statisticsService.createRecord(response.getServiceName(), response.getRequestId(), response.getClient());
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
