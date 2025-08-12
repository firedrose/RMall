package com.rmall.trade;

import com.rmall.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.spring.web.plugins.DefaultConfiguration;

@EnableFeignClients(basePackages ="com.rmall.api.client",defaultConfiguration = DefaultFeignConfig.class )
@MapperScan("com.rmall.trade.mapper")
@SpringBootApplication
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }
}