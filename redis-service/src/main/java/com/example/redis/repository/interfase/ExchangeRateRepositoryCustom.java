package com.example.redis.repository.interfase;

import com.example.redis.model.hash.ExchangeRate;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepositoryCustom {
    Optional<ExchangeRate> findLatestExchangeRateByTimestamp();
    List<ExchangeRate> getExchangeRateForPeriod(long timestamp);
    boolean existsByRedisId(Long id);

}
