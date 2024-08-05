package com.example.redis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RedisServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		HashMap<String, Double> rates = new HashMap<>();
//		rates.put("BGN",1.953166);
//		rates.put("GBP",0.843149);
//		exchangeRate.setRates(rates);
//		exchangeRateRepository.save(exchangeRate);

	}
}
