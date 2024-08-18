package com.example.db.controller;

import com.example.db.model.request.GetawayRequestDTO;
import com.example.db.model.response.RateResponseDto;
import com.example.db.service.GatewayExtService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/rates")
public class ExchangeRateJsonController {
    private final GatewayExtService gatewayExtService;

    public ExchangeRateJsonController(GatewayExtService gatewayExtService) {
        this.gatewayExtService = gatewayExtService;
    }

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RateResponseDto> getLatestRate(@RequestBody GetawayRequestDTO getawayRequestDTO) {
        RateResponseDto latestRates = gatewayExtService.getLatestRates(getawayRequestDTO);
        return ResponseEntity.ok(latestRates);
    }

    @PostMapping(value = "/history", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<RateResponseDto>> getRatesFromLastHours(@RequestBody GetawayRequestDTO getawayRequestDto) {
        return ResponseEntity.ok(gatewayExtService.getHistoryRates(getawayRequestDto));
    }
}
