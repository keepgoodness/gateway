package com.example.db.service;

import com.example.db.model.request.GetawayRequestDTO;
import com.example.db.model.response.RateResponseDto;

import java.util.Set;

public interface GatewayExtService {
    RateResponseDto getLatestRates(GetawayRequestDTO getawayRequestDTO);
    Set<RateResponseDto> getHistoryRates(GetawayRequestDTO requestInfo);
}
