package com.rmall.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.spring.web.plugins.DefaultConfiguration;

@EnableFeignClients(basePackages ="com.rmall.api.client",defaultConfiguration = DefaultConfiguration.class )
@MapperScan("com.rmall.pay.mapper")
@SpringBootApplication
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}