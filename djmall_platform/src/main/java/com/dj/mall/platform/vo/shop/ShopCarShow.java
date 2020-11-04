package com.dj.mall.platform.vo.shop;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ShopCarShow implements Serializable {
    /**
     * 购物车id
     */
    private Integer shopCarId;

    /**
     * 商户id
     */
    private Integer userId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 原价
     */
    private BigDecimal oldPrice;

    /**
     * sku属性值
     */
    private String valueAttrNames;

    /**
     * 折扣
     */
    private Integer skuRate;

    /**
     * 邮费
     */
    private String freight;

    /**
     * 现价
     */
    private BigDecimal newPrice;

    /**
     * sku表的id
     */
    private Integer productSkuId;

    /**
     * sku表的id
     */
    private Integer productSpuId;

    /**
     * 选中与未选中
     */
    private boolean checked;

    /**
     * 购物车的数量
     */
    private Integer shopCount;

    /**
     * 商品库存数量
     */
    private Integer skuCount;

}
