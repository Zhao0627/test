package com.dj.mall.auth.dto.role;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色DTO
 */
@Data
public class RoleDTO implements Serializable {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 资源ids
     */
    private List<Integer> resourceIds;

}
