package com.dj.mall.platform.vo.shop;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopCarVoReq implements Serializable {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 商品spuId
     */
    private Integer productSpuId;
    /**
     * 商品skuId
     */
    private Integer productSkuId;
    /**
     * 1选中  2未选中
     */
    private boolean checked;
    /**
     * 购买数量
     */
    private Integer shopCount;
    /**
     * 购买人id
     */
    private Integer userId;

    /**
     * sku的数组结合
     */
    private Integer[] productSkuIds;

    /**
     * 是否删除
     */
    private String isDel;

}
