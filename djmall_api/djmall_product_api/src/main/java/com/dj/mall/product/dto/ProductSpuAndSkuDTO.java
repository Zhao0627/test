package com.dj.mall.product.dto;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductSpuAndSkuDTO implements Serializable {

    /**
     * spuId
     */
    @Field
    private String id;

    /**
     * 邮费表id
     */
    @Field
    private Integer freightId;

    /**
     * 商品名称
     */
    @Field
    private String productName;

    /**
     * 商品介绍
     */
    @Field
    private String productDescribe;

    /**
     * 商品照片
     */
    @Field
    private String img;

    /**
     * 商品类型
     */
    @Field
    private String type;

    /**
     * 添加商品用户id
     */
    @Field
    private Integer userId;

    /**
     * 订单量
     */
    @Field
    private Integer orderNumber;

    /**
     * sku属性名称
     */
    @Field
    private String skuAttrValueNames;

    /**
     * 邮费
     */
    @Field
    private String freight;

    /**
     * 商品价格
     */
    @Field
    private Double skuPrice;

    /**
     * 类型名称
     */
    @Field("typeName")
    private String name;

    /**
     * 打折记录
     */
    @Field
    private Integer skuRate;

    /**
     * 商品数量
     */
    @Field
    private Integer skuCount;

    /**
     * 点赞量
     */
    @Field
    private Integer praise;


    /**
     * sku表的id
     */
    @Field
    private Integer productSkuId;

}
