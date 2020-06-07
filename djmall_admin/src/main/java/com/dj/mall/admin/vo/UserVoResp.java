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

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 用户性别
     */
    private Integer userSex;

    /**
     * 用户性别展示
     */
    private String userSexShow;

    public void setUserSexShow(String userSexShow) {
        this.userSexShow = userSexShow;
    }

    public String getUserSexShow() {
        if (userSex==1){
            return userSexShow="男";
        }
        return userSexShow="女";
    }

    /**
     * 用户级别
     */
    private Integer userLevel;

    /**
     * 用户级别
     */
    private String userLevelShow;

    /**
     * 用户状态 1 未激活 2已激活
     */
    private Integer activatedState;
}
