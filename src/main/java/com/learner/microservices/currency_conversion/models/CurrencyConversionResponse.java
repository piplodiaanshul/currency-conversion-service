package com.learner.microservices.currency_conversion.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
@ApiModel(value = "Currency conversion")
public class CurrencyConversionResponse {
  @ApiModelProperty(value = "Unique id")
  private Long id;
  private String from;
  private String to;
  private BigDecimal conversionMultiple;
  private BigDecimal quantity;
  private BigDecimal totalCalculatedAmount;
  @ApiModelProperty(value = "Port number of Forex-service instance, so that we know the source od response")
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