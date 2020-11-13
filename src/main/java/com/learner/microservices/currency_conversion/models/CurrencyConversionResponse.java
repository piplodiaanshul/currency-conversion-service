package com.learner.microservices.currency_conversion.models;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CurrencyConversionResponse {
  private Long id;
  private String from;
  private String to;
  private BigDecimal conversionMultiple;
  private BigDecimal quantity;
  private BigDecimal totalCalculatedAmount;
  private int port;
  public CurrencyConversionResponse(Long id,
                                    String from,
                                    String to,
                                    BigDecimal conversionMultiple,
                                    BigDecimal quantity,
                                    BigDecimal totalCalculatedAmount,
                                    int port) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.conversionMultiple = conversionMultiple;
    this.quantity = quantity;
    this.totalCalculatedAmount = totalCalculatedAmount;
    this.port = port;
  }
}