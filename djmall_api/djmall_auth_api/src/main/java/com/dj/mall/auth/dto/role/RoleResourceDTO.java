package com.dj.mall.auth.dto.role;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleResourceDTO implements Serializable {
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 资源id
     */
    private  Integer resourceId;
}
