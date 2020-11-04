package com.dj.mall.order.dto.detail;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetailDTO implements Serializable {

    /**
     * 订单详情表id
     */
    private Integer detailId;

    /**
     * 父级订单号
     */
    private String parentOrderNo;
    /**
     *子订单号',
     */
    private String childOrderNo;
    /**
     *'买家ID',
     */
    private Integer buyerId;
    /**
     *'商品ID',
     */
    private Integer productId;
    /**
     *'商户ID',
     */
    private Integer businessId;
    /**
     *'SKUID-针对再次购买时使用',
     */
    private Integer skuId;
    /**
     *'SKU信息(iphone-红色-64G)',
     */
    private String skuInfo;
    /**
     *'SKU价格',
     */
    private BigDecimal skuPrice;
    /**
     *'SK折扣',
     */
    private Integer skuRate;
    /**
     *购买数量',
     */
    private Integer buyCount;
    /**
     *'支付金额（含运费）',
     */
    private BigDecimal payMoney;
    /**
     *运费',
     */
    private BigDecimal freightPrice;
    /**
     * '创建时间',
     */
    private Date createTime;

    /**
     * 商品表名称
     */
    private String productName;

    /**
     * 商品类型
     */
    private String name;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付时间
     */
    private Date cancelTime;

    /**
     * 买家消息
     */
    private String buyerMessage;

    private String productNameList;
    private Integer totalBuyCount;
    private BigDecimal totalPayMoney;
    private BigDecimal totalFreight;
    private String receiverProvince;
    private String receiverCity;
    private String receiverCounty;
    private String receiverDetail;
    private String receiverName;
    private String receiverPhone;
    private BigDecimal grossMoney;
    private BigDecimal orderTotalFreight;
    private BigDecimal orderTotalMoney;
    private BigDecimal orderTotalPayMoney;
    private Integer orderTotalBuyCount;
    /**
     * 商户名称
     */
    private String userName;
    private Integer commentId;
    private Integer commentIsDel;

}
