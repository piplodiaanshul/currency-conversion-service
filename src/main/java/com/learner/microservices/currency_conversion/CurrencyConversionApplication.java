package com.learner.microservices.currency_conversion;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// to enable feign
@SpringBootApplication
@EnableFeignClients(basePackageClasses = {com.learner.microservices.currency_conversion.feign_clients.ForexClient.class})
@EnableSwagger2
public class CurrencyConversionApplication {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyConversionApplication.class, args);
  }

  @Bean  //configuring to show only selected apis in documentation
  public Docket swaggerConfig() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors
                  .basePackage("com.learner.microservices.currency_conversion.controllers"))
        .build()
        .apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo() {
    Contact owner = new Contact("Anshul Piplodia", "", "ansh.piplodia776@gmail.com");
    return new ApiInfo("Currency Conversion Apis",
                       "All the apis related to currency conversion ",
                       "1.0",
                       "",
                       owner,
                       "",
                       "",
                       Collections.emptyList());
  }
}
