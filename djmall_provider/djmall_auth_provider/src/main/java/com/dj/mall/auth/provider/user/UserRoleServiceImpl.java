package com.dj.mall.auth.provider.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.auth.api.user.UserLoginTimeService;
import com.dj.mall.auth.api.user.UserRoleService;
import com.dj.mall.auth.dto.user.UserLoginTimeDTO;
import com.dj.mall.auth.dto.user.UserRoleDTO;
import com.dj.mall.auth.entity.user.UserLoginTime;
import com.dj.mall.auth.entity.user.UserRole;
import com.dj.mall.auth.mapper.user.UserLoginTimeMapper;
import com.dj.mall.auth.mapper.user.UserRoleMapper;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户角色实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    /**
     * 根据用户id获取role_id
     *
     * @param userId
     */
    @Override
    public UserRoleDTO getByUserId(Integer userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        UserRole userRole = getOne(queryWrapper);
        return DozerUtil.map(userRole,UserRoleDTO.class);
    }

    /**
     * 新存roleId
     *
     * @param userId
     * @param roleId
     */
    @Override
    public void insert(Integer userId, Integer roleId) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        save(userRole);
    }
}
