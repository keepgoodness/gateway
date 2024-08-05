package com.example.redis.repository.implementation;

import com.example.redis.model.hash.ExchangeRate;
import com.example.redis.repository.interfase.ExchangeRateRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ExchangeRateRepositoryCustomImpl implements ExchangeRateRepositoryCustom {
    private final RedisTemplate<String, Object> redisTemplate;

    public ExchangeRateRepositoryCustomImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Optional<ExchangeRate> findLatestExchangeRateByTimestamp() {
        //start
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        //end

//        List<Object> objects = redisTemplate.opsForHash().values("ExchangeRate");
//        List<ExchangeRate> exchangeRates = objects.stream()
//                .map(o -> (ExchangeRate) o)
//                .collect(Collectors.toList());
//        return exchangeRates.stream()
//                .max(Comparator.comparingLong(ExchangeRate::getTimestamp));
        return null;
    }

    @Override
    public List<ExchangeRate> getExchangeRateForPeriod(long timestamp) {
        List<Object> objects = redisTemplate.opsForHash().values("ExchangeRate");
        List<ExchangeRate> exchangeRates = objects.stream()
                .map(o -> (ExchangeRate) o)
                .collect(Collectors.toList());
        long currentTime = System.currentTimeMillis();
        return exchangeRates.stream()
                .filter(rate -> rate.getTimestamp() >= timestamp && rate.getTimestamp() <= currentTime)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByRedisId(Long id) {
        return redisTemplate.opsForHash().hasKey("ExchangeRate", id);
    }
}
