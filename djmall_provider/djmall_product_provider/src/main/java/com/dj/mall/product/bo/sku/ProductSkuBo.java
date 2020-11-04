package com.dj.mall.product.bo.sku;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductSkuBo implements Serializable {
    private Integer id;

    /**
     * 商品id
     */
    private Integer productId;
    /**
     * sku价格
     */
    private BigDecimal skuPrice;
    /**
     * sku库存
     */
    private Integer skuCount;

    /**
     * SKU折扣,0表示无折扣
     */
    private Integer skuRate;

    /**
     * SKU状态[PRODUCT_DOWN下架,PRODUCT_UP上架]
     */
    private String skuStatus;

    /**
     *  SKU属性ID集合[id1:id2],-1代表自定
     */
    private String skuAttrIds;

    /**
     * SKU属性名集合[name1:name2]
     */
    private String skuAttrNames;

    /**
     * SKU属性值ID集合[id1:id2]-1代表自定
     */
    private String skuAttrValueIds;
    /**
     * SKU属性值名集合[name1:name2]
     */
    private String skuAttrValueNames;
    /**
     * 是否默认
     */
    private String isDefault;
}
