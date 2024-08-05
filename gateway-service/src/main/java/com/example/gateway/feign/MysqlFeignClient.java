package com.example.gateway.feign;

import com.example.gateway.configuration.OpenFeignConfig;
import com.example.gateway.model.dto.ExchangeRateDto;
import com.example.gateway.model.dto.RequestInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MysqlClient",
        url = "${mysql.api.url}",
        configuration = OpenFeignConfig.class)
public interface MysqlFeignClient {

    @PostMapping("/current")
    ExchangeRateDto sendRequestAndReceiveRates(@RequestBody RequestInfoDto requestInfoDto);
}
