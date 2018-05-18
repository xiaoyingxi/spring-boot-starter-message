package com.yuuyoo.starter.validate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ValidateApplication {

  public static void main(String[] args) {
    SpringApplication.run(ValidateApplication.class, args);
  }
}
