package com.dj.mall.admin.vo.role;

import lombok.Data;

/**
 * 角色信息展示模型
 */
@Data
public class RoleVoReq {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名字
     */
    private String roleName;

}
