package com.dj.mall.auth.api.user;

import com.dj.mall.auth.dto.user.UserLoginTimeDTO;

/**
 * 用户登录接口
 */
public interface UserLoginTimeService {

    /**
     * 用户登录记录新增
     * @param userLoginTimeDTO
     */
    void save(UserLoginTimeDTO userLoginTimeDTO);
}
