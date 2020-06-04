package com.dj.mall.admin.vo;

import lombok.Data;

/**
 * 角色信息展示模型
 */
@Data
public class RoleVoResp {

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名字
     */
    private String roleName;
}
