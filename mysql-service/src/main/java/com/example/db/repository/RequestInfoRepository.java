package com.example.db.repository;

import com.example.db.model.entity.RequestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestInfoRepository extends JpaRepository<RequestInfo, Long> {

    boolean existsByRequestId(String requestId);
}
