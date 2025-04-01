package com.rmall.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.spring.web.plugins.DefaultConfiguration;

@EnableFeignClients(basePackages ="com.rmall.api.client",defaultConfiguration = DefaultConfiguration.class )
@MapperScan("com.rmall.cart.mapper")
@SpringBootApplication
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}