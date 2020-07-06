package com.dj.mall.product.dto.spu;

import com.dj.mall.product.dto.sku.ProductSkuDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductSpuDTO implements Serializable {

    private Integer productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 运费表ID
     */
    private Integer freightId;
    /**
     * 商品描述
     */
    private String productDescribe;
    /**
     * 商品照片
     */
    private String img;
    /**
     * 商品类型（字典code）
     */
    private String type;
    /**
     * 新增人ID（用于展示判断）
     */
    private Integer userId;
    /**
     * SKU状态[0下架,1上架]
     */
    private String status;
    /**
     * 订单量
     */
    private Integer orderNumber;
    /**
     * 点赞量
     */
    private Integer praise;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 上架人的名字
     */
    private String userName;

    /**
     * 邮费
     */
    private String freight;

    /**
     * sku的list集合
     */
    private List<ProductSkuDTO> SkuList;
}
