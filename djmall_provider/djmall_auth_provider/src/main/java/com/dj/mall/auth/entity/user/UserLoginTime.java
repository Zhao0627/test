package com.dj.mall.auth.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.dozer.Mapping;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类（用户表）
 */
@TableName("user_login")
@Data
public class UserLoginTime implements Serializable {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 登录用户id
     */
    private Integer userId;

    /**
     * 注册时间
     */
    //后台到前台转换（”yyyy-MM-dd hh:mm:ss“）格式
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    //前台到后台转换Date格式
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date loginTime;


}
