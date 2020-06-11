package com.dj.mall.auth.bo.user;

import lombok.Data;
import java.util.Date;

@Data
public class UserBo {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户性别
     */
    private Integer userSex;

    /**
     * 用户级别
     */
    private Integer userLevel;

    /**
     * 用户状态
     */
    private Integer activatedState;

    /**
     * 昵称
     */
    private String nickName;


    /**
     * 密码盐
     */
    private String salt;


    /**
     * 用户级别
     */
    private String userLevelShow;

    /**
     * 用户d登录时间
     */
    private Date loginTime;

    /**
     * 用户d登录时间
     */
    private Date saveTime;

    /**
     * 用户重置密码
     */
    private String resetPwd;

    /**
     * 删除状态
     */
    private Integer isDel;


}
