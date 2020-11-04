package com.dj.mall.admin.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String userSex;

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
    private String activatedState;

    /**
     * 注册时间
     */
    private Date saveTime;
    /**
     * 注册时间
    */
    private String saveTimeShow;


    public String getSaveTimeShow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(saveTime);
    }

    /**
     * 重置密码
     */
    private String resetPwd;

    /**
     * 登陆时间
     */
    //后台到前台转换（”yyyy-MM-dd hh:mm:ss“）格式
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    //前台到后台转换Date格式
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date loginTime;

}
