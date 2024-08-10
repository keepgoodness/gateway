package com.example.db.repository;

import com.example.db.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query(value = "SELECT * FROM exchange_rate ORDER BY id DESC LIMIT 1", nativeQuery = true)
    ExchangeRate findLatestRate();
    Optional<ExchangeRate> findFirstByOrderByIdDesc();
    ExchangeRate findFirstByOrderByTimestampDesc();
    @Query("SELECT e FROM exchange_rate e WHERE e.timestamp >= :timestamp")
    List<ExchangeRate> findRatesFromTimestamp(@Param("timestamp") Timestamp timestamp);
    List<ExchangeRate> findAllByTimestampGreaterThanEqual(Timestamp timestamp);
}
