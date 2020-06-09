package com.dj.mall.auth.dto.user;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类（用户登录）
 */
@Data
public class UserLoginTimeDTO implements Serializable {

    /**
     * id
     */
    private Integer userLoginTimeId;

    /**
     * 登录用户id
     */
    private Integer userId;

    /**
     * 注册时间
     */
    private Date loginTime;


}
