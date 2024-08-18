package com.example.gateway.client;

import com.example.gateway.configuration.OpenFeignConfig;
import com.example.gateway.model.request.json.RequestInfoDto;
import com.example.gateway.model.response.RateResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "mysql-service",
        url = "${mysql.api.url}",
        configuration = OpenFeignConfig.class)
public interface MysqlClient {

    @PostMapping("/current")
    RateResponseDto sendRequestAndReceiveCurrentRates(@RequestBody RequestInfoDto requestInfoDto);

    @PostMapping("/history")
    List<RateResponseDto> sendRequestAndReceiveRatesHistory(@RequestBody RequestInfoDto requestInfo);
}
