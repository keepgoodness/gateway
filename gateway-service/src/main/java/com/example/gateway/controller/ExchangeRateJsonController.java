package com.example.gateway.controller;

import com.example.gateway.client.MysqlClient;
import com.example.gateway.model.request.RequestInfoDto;
import com.example.gateway.model.response.JsonResponseDto;
import com.example.gateway.validation.GroupA;
import com.example.gateway.validation.GroupB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/json")
public class ExchangeRateJsonController {
    private final MysqlClient mysqlClient;
    @Autowired
    public ExchangeRateJsonController(@Qualifier("com.example.gateway.client.MysqlClient") MysqlClient mysqlClient) {
        this.mysqlClient = mysqlClient;
    }

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponseDto> getLatestRate(@Validated({GroupA.class}) @RequestBody RequestInfoDto requestInfoDTO){
        JsonResponseDto ratesCurrent = mysqlClient.sendRequestAndReceiveCurrentRates(requestInfoDTO);
        return ResponseEntity.ok(ratesCurrent);
    }

    @PostMapping(value = "/history", consumes = "application/json")
    public ResponseEntity<List<JsonResponseDto>> getRatesFromLastHours(@Validated({GroupA.class, GroupB.class}) @RequestBody RequestInfoDto requestInfo){
        List<JsonResponseDto> ratesHistory = mysqlClient.sendRequestAndReceiveRatesHistory(requestInfo);
        return ResponseEntity.ok(ratesHistory);
    }
}
