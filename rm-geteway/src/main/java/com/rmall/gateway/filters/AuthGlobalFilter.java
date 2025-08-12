package com.rmall.gateway.filters;

import cn.hutool.core.text.AntPathMatcher;
import com.rmall.common.exception.UnauthorizedException;
import com.rmall.common.utils.UserContext;
import com.rmall.gateway.config.AuthProperties;
import com.rmall.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final JwtTool jwtTool;
    private final AuthProperties properties;


    private final AntPathMatcher matcher = new AntPathMatcher();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取request
        ServerHttpRequest request = exchange.getRequest();
        //2.判断是否需要登录拦截
            if(isExcludePath(request.getPath().toString())){
                return chain.filter(exchange);
            }
        //3.获取Toke
        String token=null;
        List<String> headers = request.getHeaders().get("authorization");
        if (headers != null&& !headers.isEmpty()) {
            token=headers.get(0);
        }
        //4.校验并解析Token
        Long userId=null;
        try {
             userId = JwtTool.parseToken(token);
        }
        catch (UnauthorizedException e)
            {
                ServerHttpResponse response = exchange.getResponse();
                response. setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
        }
        //5.TODO 传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();

        UserContext.setUser(userId);
        //6.放行
        return chain.filter(swe);
    }

    private boolean isExcludePath(String path) {
        for (String excludePath : properties.getExcludePaths()) {
            if (matcher.match(excludePath, path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
