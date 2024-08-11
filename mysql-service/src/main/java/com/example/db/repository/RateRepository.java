package com.example.db.repository;

import com.example.db.model.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findTopByCurrencyOrderByTimestampDesc(String currency);

    List<Rate> findAllByCurrencyAndTimestampAfterOrderByTimestampDesc(String currency, Timestamp timestamp);
}
