package com.dj.mall.admin.config;

import com.dj.mall.admin.vo.UserVoResp;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器
 */
@Component("userInterceptor")
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
/*        HttpSession session = request.getSession();
        UserVoResp user = (UserVoResp) session.getAttribute("user");
        if (user==null){
            System.out.println("已拦截");
            response.sendRedirect(request.getContextPath()+"/auth/user/toLogin");
            return false;

        }
        System.out.println("放过");*/
        return true;
    }
}
