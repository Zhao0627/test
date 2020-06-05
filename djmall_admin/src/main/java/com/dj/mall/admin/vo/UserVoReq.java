package com.dj.mall.admin.vo;

import lombok.Data;

/**
 * 用户信息展示模型
 */
@Data
public class UserVoReq {

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

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户性别
     */
    private Integer userSex;

    /**
     * 用户级别
     */
    private Integer userLevel;


}
