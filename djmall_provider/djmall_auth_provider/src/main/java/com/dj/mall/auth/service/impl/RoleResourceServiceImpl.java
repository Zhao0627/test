package com.dj.mall.auth.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.entity.role.RoleResource;
import com.dj.mall.auth.mapper.role.RoleResourceMapper;
import com.dj.mall.auth.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public void delete(List<Integer> resourceIds) throws Exception {
        roleResourceMapper.deletBatch(resourceIds);
    }
}

