package com.example.db.service;

public interface StatisticsService {

    void verifyRequest(String requestId);

    void createRecord(String serviceName, String requestId, String client);
}
