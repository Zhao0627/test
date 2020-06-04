package com.dj.mall.auth.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户DTO
 */
@Data
public class UserDTO implements Serializable {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;


    /**
     * 密码
     */
    private String userPwd;

    /**
     * 手机号
     */
    private String userPhone;

    /**
     * 邮箱
     */
    private String userEmail;

}
