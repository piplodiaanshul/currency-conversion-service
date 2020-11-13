package com.learner.microservices.currency_conversion.feign_clients;

import com.learner.microservices.currency_conversion.models.CurrencyConversionResponse;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "forex-service")
@RibbonClient(name = "forex-service")
public interface ForexClient {
  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  CurrencyConversionResponse fetchExchangeValue(@PathVariable("from") String from,
                                                @PathVariable("to") String to);
}
