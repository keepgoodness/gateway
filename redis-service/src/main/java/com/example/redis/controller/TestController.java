package com.example.redis.controller;


import com.example.redis.model.hash.ExchangeRate;
import com.example.redis.repository.ExchangeRateRepository;
import com.example.redis.repository.RequestInfoRepository;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/exchange")
public class TestController {

    private final ExchangeRateRepository exchangeRateRepository;
    private final RequestInfoRepository requestInfoRepository;

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String CURRENCY_RATE_KEY = "currency_rate";

    public TestController(ExchangeRateRepository exchangeRateRepository, RequestInfoRepository requestInfoRepository, RedisTemplate<String, Object> redisTemplate) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.requestInfoRepository = requestInfoRepository;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/add")
    public String addExchange(@RequestParam String id) {
//        if(exchangeRateRepository.findExchangeRateById(id)!= null){
//            return "Съществува ExchangeRate с това id:" + id;
//        }
        long timestamp = Instant.now().getEpochSecond();
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setTimestamp(timestamp);
        exchangeRate.setBase("EUR");
        exchangeRate.setDate("2024-07-29");
        HashMap<String, Double> rates = new HashMap<>();
        rates.put("BGN", 1.953166);
        rates.put("GBP", 0.843149);
        exchangeRate.setRates(rates);
        exchangeRateRepository.save(exchangeRate);
        return "ExchangeRate с id:" + id + " запазено!";
    }

    @GetMapping("/latest")
    public ExchangeRate getLatestExchangeRates() {
//        ArrayList<ExchangeRate> rates = new ArrayList<>();
//        exchangeRateRepository.findAll().forEach(rates::add);
//        return rates.stream()
//                .max(Comparator.comparingLong(ExchangeRate::getTimestamp))
//                .orElse(null);
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        // Get the record with the highest timestamp
        Set<Object> latestRateSet = zSetOps.reverseRange(CURRENCY_RATE_KEY, 0, 0);

        if (latestRateSet != null && !latestRateSet.isEmpty()) {
            String latestRateId = (String) latestRateSet.iterator().next();
            return (ExchangeRate) redisTemplate.opsForHash().get(CURRENCY_RATE_KEY, latestRateId);
        }
        return null;
    }
    @GetMapping("/exist")
    public String countExchangeRates(@RequestParam("id") Long id){
        if (exchangeRateRepository.existsByRedisId(id)){
            return "Съществува";
        } else {
            return "Няма такова";
        }

    }

    @GetMapping("/all")
    public List<ExchangeRate> getAll(){
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        Iterator<ExchangeRate> iterator = exchangeRateRepository.findAll().iterator();
        while (iterator.hasNext()){
            ExchangeRate next = iterator.next();
            exchangeRates.add(next);
        }
        return exchangeRates;
    }

//    @GetMapping("/delete")
//    public String delete(){
//        ExchangeRate exchangeRate = exchangeRateRepository.findExchangeRateByRequestId("b89577fe-8c37-4962-8af3-7cb89a245160");
//        exchangeRateRepository.delete(exchangeRate);
//        return "Deleted";
//    }
}
