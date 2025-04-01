package com.rmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.rmall.mapper")
@SpringBootApplication
public class RMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(RMallApplication.class, args);
    }
}