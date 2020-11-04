package com.dj.mall.platform.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import com.dj.mall.model.util.SystemConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Token拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static final String TOKEN = "TOKEN";

    @Reference
    private RedisService redisService;

    private boolean checkToken(String token) {
        if (null != token) {
            // token 有效性验证
            UserDTO userDTO=redisService.get(SystemConstant.TOKEN + token);
            if (null != userDTO) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 是ajax请求 从Harder中获取Token信息
        if (httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            // 从request的heard中获取token
            String token = httpServletRequest.getHeader(TOKEN);
            if (checkToken(token)) {
                return true;
            }
            httpServletResponse.setStatus(666);
            return false;
        } else {
            // 获取请求中的token
            String token = httpServletRequest.getParameter(TOKEN);
            if (checkToken(token)) {
                return true;
            }
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/shop/toIndexShow");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
