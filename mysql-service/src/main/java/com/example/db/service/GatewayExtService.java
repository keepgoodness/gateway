package com.example.db.service;

import com.example.db.model.request.GatewayRequestDTO;
import com.example.db.model.response.RateResponseDto;

import java.util.Set;

public interface GatewayExtService {
    RateResponseDto getLatestRates(GatewayRequestDTO gatewayRequestDTO);
    Set<RateResponseDto> getHistoryRates(GatewayRequestDTO requestInfo);
}
