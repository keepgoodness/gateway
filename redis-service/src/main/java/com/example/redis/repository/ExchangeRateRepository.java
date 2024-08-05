package com.example.redis.repository;

import com.example.redis.model.hash.ExchangeRate;
import com.example.redis.repository.interfase.ExchangeRateRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, String>, ExchangeRateRepositoryCustom {
}
