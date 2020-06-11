package com.dj.mall.admin.config;

import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component("permissionInterceptor")
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<ResourceDTO> resourceDTOList = user.getResourceDTOList();
        request.getRequestURI();
        System.out.println(request.getRequestURI());
        for (ResourceDTO resourceDTO:resourceDTOList){
            if (request.getRequestURI().equals(resourceDTO.getUrl())){
                    return true;
            }
        }
        response.sendRedirect(request.getContextPath()+"/index/to403");
        return false;
    }
}
