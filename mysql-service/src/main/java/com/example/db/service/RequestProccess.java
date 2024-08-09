package com.example.db.service;

import com.example.db.model.dto.ExchangeRateDto;
import com.example.db.model.dto.RequestInfoDTO;
import com.example.db.model.entity.ExchangeRate;

import java.util.List;

public interface RequestProccess {
    void verifyRequestIdDoesNotExist(String requestId);
    ExchangeRateDto getLatestRates(RequestInfoDTO requestInfoDTO);
    public List<ExchangeRateDto> getHistoryRates(RequestInfoDTO requestInfo);
    ExchangeRateDto convertToDto(ExchangeRate exchangeRate);
}
