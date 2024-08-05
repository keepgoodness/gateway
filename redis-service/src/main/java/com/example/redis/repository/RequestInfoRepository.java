package com.example.redis.repository;

import com.example.redis.model.hash.RequestInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestInfoRepository extends CrudRepository<RequestInfo, String> {
    boolean existsRequestInfoById(String Id);
}
