package com.example.db.service;

import com.example.db.model.request.JsonRequestDTO;
import com.example.db.model.response.JsonResponseDto;

import java.util.Set;

public interface GatewayExtService {
    JsonResponseDto getLatestRates(JsonRequestDTO jsonRequestDTO);
    Set<JsonResponseDto> getHistoryRates(JsonRequestDTO requestInfo);
}
