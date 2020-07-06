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
     * @param userDTO 去重条件
     * @return
     * @throws BusinessException
     */
    UserDTO findUserNamePhoneEmail(UserDTO userDTO)throws BusinessException;

    /**
     * 注册
     * @param userDTO 注册的用户
     */
    void insertUser(UserDTO userDTO) throws BusinessException,Exception;

    /**
     * 注册激活修改激活码
     * @param ids 激活用户id
     * @throws BusinessException
     */
    void updateActivatedState(Integer[] ids) throws BusinessException;

    /**
     * 查询所有用户
     * @param userDTO 模糊查询条件
     * @return
     */
    List<UserDTO> findUserAll(UserDTO userDTO) throws BusinessException;

    /**
     * 重置密码
     * @param ids 重置ids
     * @throws BusinessException
     */
    void resetPwd(Integer ids[], String emails[], String userName) throws Exception;

    /**
     *修改用户
     * @param userDTO
     */
    void updateUser(UserDTO userDTO) throws BusinessException;

    /**
     * 获取验证码以及发送验证码
     * @param userDTO
     */
    String getPhoneCode(UserDTO userDTO) throws BusinessException;

    /**
     * 通过手机号修改密码发送邮件
     * @param userDTO
     */
    void updatePwdByPhone(UserDTO userDTO) throws BusinessException;

    /**
     * 通过id查
     * @param id
     * @return
     */
    UserDTO findUserById(Integer id) throws BusinessException;

    /**
     * 批量级联删除
     * @param ids
     */
    void deleteUserByIds(Integer[] ids) throws BusinessException;

    /**
     * 批量级联修改
     * @param ids
     */
    void updateUserIsDelByIds(Integer[] ids) throws BusinessException;

}
