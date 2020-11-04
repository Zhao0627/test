package com.dj.mall.auth.dto.shopcar;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopCarDTO implements Serializable {

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
     * 选中未选中
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
     * 商品SkuId数组
     */
    private Integer[] productSkuIds;

    /**
     * 复选框选中的商品SkuId数组
     */
    private Integer[] checkedProductSkuIds;

    /**
     * 是否删除
     */
    private String isDel;

}
