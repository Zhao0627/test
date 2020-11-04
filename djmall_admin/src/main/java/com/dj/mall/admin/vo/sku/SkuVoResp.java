package com.dj.mall.admin.vo.sku;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkuVoResp implements Serializable {

    /**
     * sku Id
     */
    private Integer skuId;

    /**
     * 商品类型
     */
    private String productType;

    /**
     * 商品属性
     */
    private Integer attrId;

    /**
     * 商品类型
     */
    private String productShow;

    /**
     * 属性值
     */
    private String valueList;

}
