package com.dj.mall.platform.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WEB MVC 配置
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(tokenInterceptor);
        // 拦截请求
        interceptorRegistration.addPathPatterns("/**");
        // 放过请求
        interceptorRegistration.excludePathPatterns("/");
        interceptorRegistration.excludePathPatterns("/shop/toIndexShow");
        interceptorRegistration.excludePathPatterns("/shop/show2");
        interceptorRegistration.excludePathPatterns("/shop/toDetails");
        interceptorRegistration.excludePathPatterns("/shop/getCount");
        interceptorRegistration.excludePathPatterns("/order/myOrderShow");
        interceptorRegistration.excludePathPatterns("/order/receiver/siteShow");
        interceptorRegistration.excludePathPatterns("/consumer/toLogin");
        interceptorRegistration.excludePathPatterns("/consumer/phoneLogin");
        interceptorRegistration.excludePathPatterns("/consumer/login");
        interceptorRegistration.excludePathPatterns("/consumer/getPhoneCode");
        interceptorRegistration.excludePathPatterns("/consumer/getUserSalt");
        interceptorRegistration.excludePathPatterns("/static/**");
    }
}
