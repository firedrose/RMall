package com.rmall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.spring.web.plugins.DefaultConfiguration;

@EnableFeignClients(basePackages ="com.rmall.api.client",defaultConfiguration = DefaultConfiguration.class )
@MapperScan("com.rmall.user.mapper")
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}