package com.dj.mall.dict.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDataBo implements Serializable {

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
