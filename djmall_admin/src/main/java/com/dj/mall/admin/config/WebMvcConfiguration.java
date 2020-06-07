package com.dj.mall.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@DependsOn("userInterceptor")
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        //把拦截器加入到spring容器中
        InterceptorRegistration interceptorRegistration = interceptorRegistry.addInterceptor(userInterceptor);
        //对拦截器进行配置

        //拦截的方法
        interceptorRegistration.addPathPatterns("/**");
        //放过
        interceptorRegistration.excludePathPatterns("/auth/user/toLogin");
        interceptorRegistration.excludePathPatterns("/auth/user/login");
        interceptorRegistration.excludePathPatterns("/auth/user/toInsert");
        interceptorRegistration.excludePathPatterns("/auth/user/insert");
        interceptorRegistration.excludePathPatterns("/auth/user/findUserNamePhoneEmail");
        interceptorRegistration.excludePathPatterns("/auth/user/getUserSalt");
        interceptorRegistration.excludePathPatterns("/auth/user/toUpdateState");
        interceptorRegistration.excludePathPatterns("/static/**");
    }
}
