package com.dj.mall.admin.vo;

import lombok.Data;

/**
 * 资源管理Vo类
 */
@Data
public class ResourceVoReq {

    /**
     * 资源id
     */
    private Integer resourceId;

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


    /**
     *  展示位置
     */
    private String target;



}
