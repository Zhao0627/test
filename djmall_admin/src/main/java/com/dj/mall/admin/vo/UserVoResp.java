package com.dj.mall.admin.vo;

import lombok.Data;

/**
 * 用户信息展示模型
 */

@Data
public class UserVoResp {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     *密码
     */
    private String userPwd;

    /**
     *手机号
     */
    private String userPhone;

    /**
     * 邮箱
     */
    private String userEmail;

}
