package com.example.db.service.implementation;

import com.example.db.exception.ResourceAlreadyExistsException;
import com.example.db.model.entity.Statistic;
import com.example.db.repository.StatisticRepository;
import com.example.db.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger LOGGER  = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    private final StatisticRepository statisticRepository;

    public StatisticsServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public void verifyRequest(String requestId) {
        if (statisticRepository.existsByRequestId(requestId)) {
            throw new ResourceAlreadyExistsException(requestId);
        }
    }

    @Override
    public void createRecord(String serviceName, String requestId, String client) {
        Statistic statistic = new Statistic();
        statistic.setRequestId(requestId);
        statistic.setServiceName(serviceName);
        statistic.setClient(client);
        statistic.setTimestamp(Timestamp.from(Instant.now()));
        statisticRepository.save(statistic);
        LOGGER.info(String.format("Statistics record for service '%s' created successfully", statistic.getServiceName()));
    }
}
