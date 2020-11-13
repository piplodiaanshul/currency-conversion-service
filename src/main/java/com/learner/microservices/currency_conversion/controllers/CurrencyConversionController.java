package com.learner.microservices.currency_conversion.controllers;

import com.learner.microservices.currency_conversion.feign_clients.ForexClient;
import com.learner.microservices.currency_conversion.models.CurrencyConversionResponse;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

  @Autowired
  private ForexClient forexClient;

  @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversionResponse convertCurrency(@PathVariable String from,
                                                    @PathVariable String to,
                                                    @PathVariable BigDecimal quantity) {
    CurrencyConversionResponse currencyConversionResponse =
        forexClient.fetchExchangeValue(from, to);
    BigDecimal conversionFactor = currencyConversionResponse.getConversionMultiple();
    BigDecimal totalCalculatedAmount = quantity.multiply(conversionFactor);
    return new CurrencyConversionResponse(currencyConversionResponse.getId(),
                                          from,
                                          to,
                                          conversionFactor,
                                          quantity,
                                          totalCalculatedAmount,
                                          currencyConversionResponse.getPort());
  }

}