package com.dj.mall.auth.entity.role;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("djmall_auth_role_resource")
public class RoleResource implements Serializable {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 资源id
     */
    private  Integer resourceId;

}
