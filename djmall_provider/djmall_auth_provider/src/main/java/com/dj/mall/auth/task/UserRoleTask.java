package com.dj.mall.auth.task;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.auth.api.role.RoleService;
import com.dj.mall.auth.dto.resource.ResourceDTO;
import com.dj.mall.auth.dto.role.RoleDTO;
import com.dj.mall.cmpt.api.RedisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoleTask {

    @Autowired
    private RoleService roleService;

    @Reference
    private RedisService redisService;

    @EventListener(ApplicationReadyEvent.class)
    public void instUserRoleResource(ApplicationReadyEvent applicationReadyEvent) throws Exception{
        List<RoleDTO> roleAll = roleService.findRoleAll();
        for (RoleDTO role:roleAll) {
            List<ResourceDTO> resourceByRoleId = roleService.findResourceByRoleId(role.getRoleId());
            redisService.pushHash("ALL_ROLE","role"+role.getRoleId(),resourceByRoleId);
        }
    }

}
