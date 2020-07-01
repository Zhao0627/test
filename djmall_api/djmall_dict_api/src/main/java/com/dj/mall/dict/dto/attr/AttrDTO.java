package com.dj.mall.dict.dto.attr;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttrDTO implements Serializable{

    /**
     * 商品属性id
     */
    private Integer attrId;

    /**
     * 商品属性名称
     */
    private String attrName;

    /**
     * 属性值id
     */
    private Integer valueId;

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
