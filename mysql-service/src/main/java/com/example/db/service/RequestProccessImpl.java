package com.example.db.service;

import com.example.db.exception.ResourceAlreadyExistsException;
import com.example.db.model.dto.ExchangeRateDto;
import com.example.db.model.dto.StatisticDTO;
import com.example.db.model.entity.ExchangeRate;
import com.example.db.model.entity.Statistic;
import com.example.db.repository.ExchangeRateRepository;
import com.example.db.repository.StatisticRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestProccessImpl implements RequestProccess {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestProccessImpl.class);

    private final ExchangeRateRepository exchangeRateRepository;
    private final StatisticRepository statisticRepository;
    private final ModelMapper modelMapper;


    public RequestProccessImpl(ExchangeRateRepository exchangeRateRepository,
                               StatisticRepository statisticRepository, ModelMapper modelMapper) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.statisticRepository = statisticRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void verifyRequestIdDoesNotExist(String requestId) {
        if (statisticRepository.existsByRequestId(requestId)) {
            throw new ResourceAlreadyExistsException(requestId);
        }
    }

    @Override
    public ExchangeRateDto getLatestRates(StatisticDTO statisticDTO) {
        Statistic statistic = modelMapper.map(statisticDTO, Statistic.class);
        ExchangeRateDto exchangeRateDto = null;

        Optional<ExchangeRate> optional = exchangeRateRepository.findFirstByOrderByIdDesc();
        if (optional.isPresent()) {
            ExchangeRate exchangeRate = optional.get();
            exchangeRateDto = convertToDto(exchangeRate);
        }

        statisticRepository.save(statistic);
        return exchangeRateDto;
    }

    @Override
    public List<ExchangeRateDto> getHistoryRates(StatisticDTO statisticDto) {
        long periodStart = Instant.now().minus(statisticDto.getPeriod(), ChronoUnit.HOURS).getEpochSecond();
        List<ExchangeRate> rates = exchangeRateRepository.findRatesFromTimestamp(periodStart);
        Statistic statistic = convertToEntity(statisticDto);
        statisticRepository.save(statistic);

        return rates.stream()
                .map(element -> convertToDto(element))
                .collect(Collectors.toList());
    }

    @Override
    public ExchangeRateDto convertToDto(ExchangeRate exchangeRate) {
        try{
            ExchangeRateDto ratesDto = modelMapper.map(exchangeRate, ExchangeRateDto.class);
            return ratesDto;
        } catch (Exception ex){
            LOGGER.error("ModelMapper failed to map ExchangeRate {}", exchangeRate, ex);
            throw  new IllegalStateException("Failed to map ExchangeRate to ExchangeRateDto");
        }
    }

    @Override
    public Statistic convertToEntity(StatisticDTO statisticDTO) {
        try {
            Statistic statisticInfo = modelMapper.map(statisticDTO, Statistic.class);
            return statisticInfo;
        } catch (Exception ex){
            LOGGER.error("ModelMapper failed to map StatisticDTO {}", statisticDTO, ex);
            throw  new IllegalStateException("Failed to map StatisticDTO to Statistic");
        }
    }
}
