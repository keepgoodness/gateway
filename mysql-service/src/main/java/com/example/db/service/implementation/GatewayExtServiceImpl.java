package com.example.db.service.implementation;

import com.example.db.model.request.JsonRequestDTO;
import com.example.db.model.entity.Rate;
import com.example.db.model.response.JsonResponseDto;
import com.example.db.service.RateService;
import com.example.db.service.GatewayExtService;
import com.example.db.service.StatisticsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GatewayExtServiceImpl implements GatewayExtService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayExtServiceImpl.class);
    private final StatisticsService statisticsService;
    private final RateService rateService;
    private final ModelMapper modelMapper;


    public GatewayExtServiceImpl(StatisticsService statisticsService,
                                 RateService rateService,
                                 ModelMapper modelMapper) {
        this.statisticsService = statisticsService;
        this.rateService = rateService;
        this.modelMapper = modelMapper;
    }

    private JsonResponseDto convertToDto(Rate rate) {
        try{
            return modelMapper.map(rate, JsonResponseDto.class);
        } catch (Exception ex){
            LOGGER.error("ModelMapper failed to map Rate {}", rate, ex);
            throw  new IllegalStateException("Failed to map Rate to ExchangeDto");
        }
    }

    @Override
    public JsonResponseDto getLatestRates(JsonRequestDTO request) {
        statisticsService.verifyRequest(request.getRequestId());
        statisticsService.createRecord("EXT_SERVICE_1", request.getRequestId(), request.getClient());
        return convertToDto(rateService.getLatestRateForCurrency(request.getCurrency()));
    }

    @Override
    public Set<JsonResponseDto> getHistoryRates(JsonRequestDTO request) {
        statisticsService.verifyRequest(request.getRequestId());
        statisticsService.createRecord("EXT_SERVICE_1", request.getRequestId(), request.getClient());
        return rateService.getHistoryRatesForCurrency(request.getCurrency(), request.getPeriod()).stream()
                .map(this::convertToDto).collect(Collectors.toSet());
    }
}
