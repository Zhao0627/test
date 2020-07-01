package com.dj.mall.admin.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDataVoReq implements Serializable {
    /**
     * 基础数据表code
     */
    private String code;

    /**
     * 基础数据表名称
     */
    private String name;

    /**
     * 基础数据表父级code
     */
    private String pCode;
}