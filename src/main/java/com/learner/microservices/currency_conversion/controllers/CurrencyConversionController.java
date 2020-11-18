package com.learner.microservices.currency_conversion.controllers;

import com.learner.microservices.currency_conversion.feign_clients.ForexClient;
import com.learner.microservices.currency_conversion.models.CurrencyConversionResponse;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class CurrencyConversionController {

  @Autowired
  private ForexClient forexClient;

  @Value("${messages.welcome:Welcome}")
  private String welcomeMessage;

  @ApiOperation(value = "Fetch welcome message",
                notes = "Fetches a configurable welome message from config server",
                response = String.class,
                tags = {"configurable-greeting-message"})
  @GetMapping("welcome")
  public String getWelcomeMessage() {
    return welcomeMessage;
  }

  @ApiOperation(value = "Fetch converted amount",
                notes = "Fetch the converted amount for the currency from Forex micro-service",
                response = CurrencyConversionResponse.class)
  @GetMapping("currency-converter/from/{from}/to/{to}/quantity/{quantity}")
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