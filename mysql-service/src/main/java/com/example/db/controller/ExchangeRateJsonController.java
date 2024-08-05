package com.example.db.controller;

import com.example.db.model.dto.ExchangeRateDto;
import com.example.db.model.dto.RequestInfoDTO;
//import jakarta.validation.Valid;
import com.example.db.service.RequestProccess;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class ExchangeRateJsonController {
    private final RequestProccess requestProccess;

    public ExchangeRateJsonController(RequestProccess requestProccess) {
        this.requestProccess = requestProccess;
    }

    @PostMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeRateDto getLatestRate(@RequestBody RequestInfoDTO requestInfoDTO){
        requestProccess.verifyRequestIdDoesNotExist(requestInfoDTO.getRequestId());
        ExchangeRateDto latestRatesDto = requestProccess.getLatestRates();
        requestProccess.saveRequestInfo(requestInfoDTO, latestRatesDto.getId());
        return latestRatesDto;
    }
//
//    @PostMapping(value = "/history", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<ExchangeRate>> getRatesFromLastHours(@RequestBody @Valid RequestInfoDTO requestInfo){
//        long timestamp = Instant.now().minus(requestInfo.getPeriod(), ChronoUnit.HOURS).getEpochSecond();
//        return ResponseEntity.ok(exchangeRateRepository.findRatesFromTimestamp(timestamp));
//    }
}
