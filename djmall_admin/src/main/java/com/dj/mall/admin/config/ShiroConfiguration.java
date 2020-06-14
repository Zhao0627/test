package com.dj.mall.admin.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import java.util.HashMap;
import java.util.Map;

@Configuration
@DependsOn("shiroRealm")
public class ShiroConfiguration {

    /**
     * 自定义realm
     */
    @Autowired
    private ShiroRealm shiroRealm;

    /**
     * 安全管理器
     */
    @Bean
    DefaultWebSecurityManager securityManager (){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm);
        return defaultWebSecurityManager;
    }

    /**
     * shiro过滤工厂
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        //shiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //默认登录url
        shiroFilterFactoryBean.setLoginUrl("/auth/user/toLogin");
        //成功之后跳转路径
        shiroFilterFactoryBean.setSuccessUrl("/index/toIndex");
        //设置未授权界面，授权认证失败会访问该url
        shiroFilterFactoryBean.setUnauthorizedUrl("/index/to403");
        Map<String,String> filters = new HashMap<>();
        filters.put("/auth/user/login","anon"); //表示不需要认证
        filters.put("/auth/user/toLogin","anon"); //表示不需要认证
        filters.put("/auth/user/toInsert","anon"); //表示不需要认证
        filters.put("/auth/user/insert","anon"); //表示不需要认证
        filters.put("/auth/user/findUserNamePhoneEmail","anon"); //表示不需要认证
        filters.put("/auth/user/getUserSalt","anon"); //表示不需要认证
        filters.put("/auth/user/toUpdateState","anon"); //表示不需要认证
        filters.put("/auth/user/toUpdatePwd","anon"); //表示不需要认证
        filters.put("/auth/user/updatePwdByPhone","anon"); //表示不需要认证
        filters.put("/auth/user/toForgetPwd","anon"); //表示不需要认证
        filters.put("/static/**","anon"); //表示不需要认证
        filters.put("/**","authc");//必须认证才能访问
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
        return shiroFilterFactoryBean;
    }



}
