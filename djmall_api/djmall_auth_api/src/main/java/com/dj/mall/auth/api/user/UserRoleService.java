package com.dj.mall.auth.api.user;

import com.dj.mall.auth.dto.user.UserLoginTimeDTO;
import com.dj.mall.auth.dto.user.UserRoleDTO;
import com.dj.mall.model.base.BusinessException;

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

    /**
     * 授权操作
     * @param roleId
     * @param id
     * @throws BusinessException
     */
    void mandate(Integer roleId, Integer id) throws BusinessException;
}
