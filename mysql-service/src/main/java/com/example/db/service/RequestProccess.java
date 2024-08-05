package com.example.db.service;

import com.example.db.model.dto.ExchangeRateDto;
import com.example.db.model.dto.RequestInfoDTO;

public interface RequestProccess {
    void verifyRequestIdDoesNotExist(String requestId);
    void saveRequestInfo(RequestInfoDTO requestInfoDTO, Long exchangeRateId);
    public ExchangeRateDto getLatestRates();
}
