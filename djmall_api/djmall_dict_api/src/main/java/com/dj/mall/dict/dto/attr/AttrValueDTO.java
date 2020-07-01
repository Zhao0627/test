package com.dj.mall.dict.dto.attr;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttrValueDTO implements Serializable {

    /**
     * 商品属性值id
     */
    private Integer valueId;

    /**
     * 商品属性id
     */
    private Integer attrId;

    /**
     * 属性值
     */
    private String attrValue;

}
