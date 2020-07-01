package com.dj.mall.dict.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttrBo implements Serializable {

    /**
     * 商品属性id
     */
    private Integer attrId;

    /**
     * 商品属性名称
     */
    private String attrName;

    /**
     * 属性值
     */
    private String attrValue;

    /**
     * 属性集合
     */
    private String ValueList;

    /**
     * checked:1 勾选 2 未勾选
     * 判断是否勾选（默认不勾选）
     */
    private Integer checked;
}
