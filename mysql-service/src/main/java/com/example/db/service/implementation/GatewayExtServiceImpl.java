package com.example.db.service.implementation;

import com.example.db.model.request.GatewayRequestDTO;
import com.example.db.model.entity.Rate;
import com.example.db.model.response.RateResponseDto;
import com.example.db.publisher.StatisticsPublisher;
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
    private final StatisticsPublisher statisticsPublisher;
    private final RateService rateService;
    private final ModelMapper modelMapper;


    public GatewayExtServiceImpl(StatisticsService statisticsService,
                                 StatisticsPublisher statisticsPublisher, RateService rateService,
                                 ModelMapper modelMapper) {
        this.statisticsService = statisticsService;
        this.statisticsPublisher = statisticsPublisher;
        this.rateService = rateService;
        this.modelMapper = modelMapper;
    }

    private RateResponseDto convertToDto(Rate rate) {
        try{
            return modelMapper.map(rate, RateResponseDto.class);
        } catch (Exception ex){
            LOGGER.error("ModelMapper failed to map Rate {}", rate, ex);
            throw  new IllegalStateException("Failed to map Rate to ExchangeDto");
        }
    }

    @Override
    public RateResponseDto getLatestRates(GatewayRequestDTO request) {
        statisticsService.verifyRequest(request.getRequestId());
        statisticsPublisher.sendMessage(request);
        return convertToDto(rateService.getLatestRateForCurrency(request.getCurrency()));
    }

    @Override
    public Set<RateResponseDto> getHistoryRates(GatewayRequestDTO request) {
        statisticsService.verifyRequest(request.getRequestId());
        statisticsPublisher.sendMessage(request);
        return rateService.getHistoryRatesForCurrency(request.getCurrency(), request.getPeriod()).stream()
                .map(this::convertToDto).collect(Collectors.toSet());
    }
}
