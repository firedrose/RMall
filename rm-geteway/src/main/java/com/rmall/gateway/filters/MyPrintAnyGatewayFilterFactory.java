package com.rmall.gateway.filters;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class MyPrintAnyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyPrintAnyGatewayFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter(new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println("MyPrintAnyGatewayFilterFactory is running");
                return chain.filter(exchange);
            }
        }, 1);
    }

    //自定义配置类,变量名与下面呼应
    @Data
    public static class Config {
        private String a;
        private String b;
        private String c;
    }

    //将变量名返回给spring cloud gateway，spring cloud gateway会根据变量名从配置文件中获取值,按照变量顺序获取
    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("a", "b", "c");
    }

    //将Config字节码传递给父类,父类会根据字节码读取yaml中的值并返回给子类
    public MyPrintAnyGatewayFilterFactory() {
        super(Config.class);
    }
}
