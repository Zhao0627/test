package com.dj.mall.auth.api.user;

import com.dj.mall.auth.dto.user.UserLoginTimeDTO;
import com.dj.mall.auth.dto.user.UserRoleDTO;

/**
 * 用户角色接口
 */
public interface UserRoleService {

    /**
     *根据用户id获取role_id
     * @param userId
     */
    UserRoleDTO getByUserId(Integer userId);

    /**
     * 新存roleId
     * @param userId
     * @param roleId
     */
    void insert(Integer userId, Integer roleId);
}
