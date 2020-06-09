package com.dj.mall.auth.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类（用户角色关联表）
 */
@TableName("djmall_auth_user_role")
@Data
public class UserRole implements Serializable {

    /**
     *用户id
     */
    private Integer userId;

    /**
     * 角色id
     */
    private Integer roleId;

}
