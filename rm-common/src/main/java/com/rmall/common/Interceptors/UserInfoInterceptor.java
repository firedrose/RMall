package com.rmall.common.Interceptors;

import cn.hutool.core.util.StrUtil;

import com.rmall.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        //1.获取登录用户
        String userInfo = request.getHeader("user-info");
        //2.判断是否获取了用户,如果是,将用户存入ThreadLocal
        if(StrUtil.isNotBlank(userInfo)){
            UserContext.setUser(Long.valueOf(userInfo));
        }
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,Object handler,Exception e) throws Exception{
        UserContext.removeUser();
    }

}
