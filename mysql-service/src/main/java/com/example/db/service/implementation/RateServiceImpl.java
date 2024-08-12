package com.example.db.service.implementation;

import com.example.db.exception.DataNotFoundException;
import com.example.db.model.entity.Rate;
import com.example.db.repository.RateRepository;
import com.example.db.service.RateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    private static final Logger LOGGER  = LoggerFactory.getLogger(RateServiceImpl.class);
    private final RateRepository repository;

    public RateServiceImpl(RateRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(key = "#currency", value = "rate", sync = true)
    public Rate getLatestRateForCurrency(String currency) {
        Rate rate = repository.findTopByCurrencyOrderByTimestampDesc(currency)
                .orElseThrow(() -> new DataNotFoundException(currency));
        LOGGER.info(String.format("Requested latest rate for currency: '%s'", currency));
        return rate;
    }

    @Override
    @Cacheable(key = "#currency + '-' + #period", value = "rateList", sync = true)
    public List<Rate> getHistoryRatesForCurrency(String currency, Integer period) {
        Instant periodStart = Instant.now().minus(period, ChronoUnit.HOURS);
        LOGGER.info(String.format("Requested history rates for currency: '%s'", currency));
        return repository.findAllByCurrencyAndTimestampAfterOrderByTimestampDesc(currency, Timestamp.from(periodStart));
    }
}
