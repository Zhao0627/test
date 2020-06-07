package com.dj.mall.auth.api.user;


import com.dj.mall.auth.dto.user.UserDTO;
import com.dj.mall.model.base.BusinessException;

import java.util.List;

/**
 * 用户信息接口
 */
public interface UserService {
    /**
     * 根据用户名获取信息（登录）
     * @param userDTO 用户传递信息（用户名和密码）
     * @return
     * @throws BusinessException
     */
    UserDTO getUserByUserName(UserDTO userDTO) throws BusinessException;

    /**
     * 注册去重
     * @param userDTO
     * @return
     * @throws BusinessException
     */
    UserDTO findUserNamePhoneEmail(UserDTO userDTO)throws BusinessException;

    /**
     * 注册
     * @param userDTO
     */
    void insertUser(UserDTO userDTO) throws Exception;

    /**
     * 注册激活修改激活码
     * @param id
     * @throws BusinessException
     */
    void updateActivatedState(Integer id) throws BusinessException;

    /**
     * 查询有用户
     * @param userDTO
     * @return
     */
    List<UserDTO> findUserAll(UserDTO userDTO) throws BusinessException;
}
