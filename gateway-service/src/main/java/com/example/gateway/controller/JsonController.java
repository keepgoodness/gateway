package com.example.gateway.controller;

import com.example.gateway.model.request.json.RequestInfoDto;
import com.example.gateway.model.response.RateResponseDto;
import com.example.gateway.service.ExtService;
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
public class JsonController {
    private final ExtService service;
    @Autowired
    public JsonController(@Qualifier("jsonServiceImpl") ExtService service) {
        this.service = service;
    }

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RateResponseDto> getLatestRate(@Validated({GroupA.class}) @RequestBody RequestInfoDto requestInfoDTO){
//        RateResponseDto ratesCurrent = mysqlClient.sendRequestAndReceiveCurrentRates(requestInfoDTO);
        return ResponseEntity.ok(((RateResponseDto) service.getLatestRate(requestInfoDTO)));
    }

    @PostMapping(value = "/history", consumes = "application/json")
    public ResponseEntity<List<RateResponseDto>> getRatesFromLastHours(@Validated({GroupA.class, GroupB.class}) @RequestBody RequestInfoDto requestInfo){
//        List<RateResponseDto> ratesHistory = mysqlClient.sendRequestAndReceiveRatesHistory(requestInfo);
        return ResponseEntity.ok(service.getHistoryRates(requestInfo));
    }
}
