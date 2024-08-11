package com.example.db.repository;

import com.example.db.model.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    boolean existsByRequestId(String requestId);
}
