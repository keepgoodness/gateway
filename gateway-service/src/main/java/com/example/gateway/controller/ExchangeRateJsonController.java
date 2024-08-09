package com.example.gateway.controller;

import com.example.gateway.feign.MysqlFeignClient;
import com.example.gateway.model.dto.ExchangeRateDto;
import com.example.gateway.model.dto.RequestInfoDto;
import com.example.gateway.validation.GroupA;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/json")
public class ExchangeRateJsonController {
    private final MysqlFeignClient mysqlClient;
    public ExchangeRateJsonController(MysqlFeignClient mysqlFeignClient) {
        this.mysqlClient = mysqlFeignClient;
    }

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeRateDto> getLatestRate(@Validated({GroupA.class}) @RequestBody RequestInfoDto requestInfoDTO){
        // тук да се проверява и взима първо от redis-service за бързодействие.
        ExchangeRateDto ratesCurrent = mysqlClient.sendRequestAndReceiveCurrentRates(requestInfoDTO);
        return ResponseEntity.ok(ratesCurrent);
    }

//    @PostMapping(value = "/history", consumes = "application/json")
//    public ResponseEntity<List<ExchangeRateDto>> getRatesFromLastHours(@Validated({GroupA.class, GroupB.class}) @RequestBody RequestInfoDto requestInfo){
//        List<ExchangeRateDto> ratesHistory = mysqlClient.sendRequestAndReceiveRatesHistory(requestInfo);
//        return ResponseEntity.ok(ratesHistory);
//    }
}
