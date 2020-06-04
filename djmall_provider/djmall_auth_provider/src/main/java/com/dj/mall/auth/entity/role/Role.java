package com.dj.mall.auth.entity.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.dozer.Mapping;

import java.io.Serializable;

/**
 * 实体类（角色表）
 */
@TableName("djmall_auth_role")
@Data
public class Role implements Serializable {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @Mapping("roleId")
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

}
