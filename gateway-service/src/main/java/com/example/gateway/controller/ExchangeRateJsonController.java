package com.example.gateway.controller;

import com.example.gateway.feign.MysqlFeignClient;
import com.example.gateway.model.dto.ExchangeRateDto;
import com.example.gateway.model.dto.RequestInfoDto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/json")
public class ExchangeRateJsonController {
    private final MysqlFeignClient mysqlClient;
    private final ModelMapper modelMapper;

    public ExchangeRateJsonController(
            MysqlFeignClient mysqlFeignClient,
            ModelMapper modelMapper) {
        this.mysqlClient = mysqlFeignClient;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExchangeRateDto> getLatestRate(@RequestBody RequestInfoDto requestInfoDTO){
        // тук да се проверява и взима първо от redis-service за бързодействие.
        ExchangeRateDto response = mysqlClient.sendRequestAndReceiveRates(requestInfoDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/history", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExchangeRateDto>> getRatesFromLastHours(@RequestBody @Valid RequestInfoDto requestInfo){
//        long timestamp = Instant.now().minus(requestInfo.getPeriod(), ChronoUnit.HOURS).getEpochSecond();
//        return ResponseEntity.ok(exchangeRateRepository.findRatesFromTimestamp(timestamp));
        return null;
    }
}
