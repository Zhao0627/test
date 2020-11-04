package com.dj.mall.admin.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ShiroRealm extends AuthorizingRealm {

    @Reference
    private RedisService redisService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        UserDTO user = (UserDTO) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<ResourceDTO> allRole = redisService.getHash("ALL_ROLE", "role"+user.getUserLevel());
        for (ResourceDTO resourceDTO:allRole){
            simpleAuthorizationInfo.addStringPermission(resourceDTO.getResourceCode());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = new String((char[]) authenticationToken.getCredentials());
        return new SimpleAuthenticationInfo(userName,userPwd, getName());
    }
}
