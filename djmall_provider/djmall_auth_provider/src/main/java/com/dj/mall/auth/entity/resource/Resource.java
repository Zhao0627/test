package com.dj.mall.auth.entity.resource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.dozer.Mapping;

import java.io.Serializable;

/**
 * 资源实体类
 */
@Data
@TableName("djmall_auth_resource")
public class Resource implements Serializable {

    /**
     * 资源id
     */
    @TableId(type = IdType.AUTO)
    @Mapping("resourceId")
    private Integer id;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 资源path
     */
    private String url;

    /**
     * 父id
     */
    private Integer pId;

    /**
     * 资源编码
     */
    private String resourceCode;

    /**
     * 资源类型1、菜单，2、按钮
     */
    private Integer resourceType;

}
