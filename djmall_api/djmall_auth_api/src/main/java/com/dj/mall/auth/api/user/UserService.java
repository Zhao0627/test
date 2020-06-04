package com.dj.mall.auth.api.user;


import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.model.base.BusinessException;

public interface UserService {
    /**
     * 根据用户名获取信息（登录）
     * @param userDTO 用户传递信息（用户名和密码）
     * @return
     * @throws BusinessException
     */
    UserDTO getUserByUserName(UserDTO userDTO) throws BusinessException;

}