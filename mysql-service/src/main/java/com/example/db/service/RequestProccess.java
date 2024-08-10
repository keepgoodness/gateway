package com.example.db.service;

import com.example.db.model.dto.ExchangeRateDto;
import com.example.db.model.dto.StatisticDTO;
import com.example.db.model.entity.ExchangeRate;
import com.example.db.model.entity.Statistic;

import java.util.List;

public interface RequestProccess {
    void verifyRequestIdDoesNotExist(String requestId);
    ExchangeRateDto getLatestRates(StatisticDTO statisticDTO);
    public List<ExchangeRateDto> getHistoryRates(StatisticDTO requestInfo);
    ExchangeRateDto convertToDto(ExchangeRate exchangeRate);
    Statistic convertToEntity(StatisticDTO statisticDTO);
}
