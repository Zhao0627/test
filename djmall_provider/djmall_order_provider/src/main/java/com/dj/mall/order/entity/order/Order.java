package com.dj.mall.order.entity.order;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单总表
 */

@Data
@Builder
@TableName("djmall_order")
public class Order {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 购买者id
     */
    private Integer buyerId;

    /**
     * 订单总金额
     */
    private BigDecimal totalMoney;

    /**
     *  实付总金额
     */
    private BigDecimal totalPayMoney;

    /**
     * 总运费
     */
    private BigDecimal totalFreight;

    /**
     * 总购买数量
     */
    private Integer totalBuyCount;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 收货信息--省
     */
    private String receiverProvince;

    /**
     * 收货信息--市
     */
    private String receiverCity;

    /**
     * 收货信息--区/县
     */
    private String receiverCounty;

    /**
     * 收货信息--收货人
     */
    private String receiverName;

    /**
     * 收货信息--手机号
     */
    private String receiverPhone;

    /**
     * 收货信息--地址明细
     */
    private String receiverDetail;

    /**
     * 订单状态--[已取消/待支付/待发货/已发货/确认收货/已完成]
     */
    private String orderStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
