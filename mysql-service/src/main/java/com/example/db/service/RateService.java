package com.example.db.service;

import com.example.db.model.entity.Rate;

import java.util.List;

public interface RateService {
    Rate getLatestRateForCurrency(String currency);
    List<Rate> getHistoryRatesForCurrency(String currency, Integer period);
}
