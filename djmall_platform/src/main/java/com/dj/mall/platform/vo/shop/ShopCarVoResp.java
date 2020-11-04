package com.dj.mall.platform.vo.shop;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShopCarVoResp implements Serializable {
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
    private Boolean checked;
    /**
     * 购买数量
     */
    private Integer shopCount;
    /**
     * 购买人id
     */
    private Integer userId;

    /**
     * 是否删除
     */
    private String isDel;

}
