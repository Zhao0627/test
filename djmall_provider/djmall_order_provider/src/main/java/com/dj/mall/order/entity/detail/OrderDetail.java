package com.dj.mall.order.entity.detail;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.dozer.Mapping;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@TableName("djmall_order_detail")
public class OrderDetail {

    /**
     * 订单详情表id
     */
    @TableId(type = IdType.AUTO)
    @Mapping("detailId")
    private Integer id;

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
     * 详情表状态
     */
    private String orderStatus;

    /**
     * 支付时间
     */
    private Date cancelTime;

    /**
     * 买家消息
     */
    private String buyerMessage;


}
