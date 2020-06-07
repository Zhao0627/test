package com.dj.mall.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.mall.auth.entity.role.RoleResource;

import java.util.List;

public interface RoleResourceService extends IService<RoleResource> {

    void delete(List<Integer> resourceIds) throws Exception;
}
