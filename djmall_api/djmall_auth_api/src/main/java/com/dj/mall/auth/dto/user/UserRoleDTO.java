package com.dj.mall.auth.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类（用户角色）
 */
@Data
public class UserRoleDTO implements Serializable {

    /**
     * id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;


}
