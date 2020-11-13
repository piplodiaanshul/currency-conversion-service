package com.learner.microservices.currency_conversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// to enable feign
@SpringBootApplication
@EnableFeignClients(basePackageClasses = {com.learner.microservices.currency_conversion.feign_clients.ForexClient.class})
public class CurrencyConversionApplication {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyConversionApplication.class, args);
  }

}
