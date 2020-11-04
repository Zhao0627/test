package com.dj.mall.order.entity.info;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@TableName("djmall_order_info")
public class OrderInfo {

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 父订单号',
     */
    private String parentOrderNo;
    /**
     * 买家ID',
     */
    private Integer buyerId;

    /**
     * 商户ID',
     */
    private Integer businessId;

    /**
     * '商品ID,按商户拆可以去掉'
     */
    private Integer productId;
    /**
     * 订单总金额',
     */
    private BigDecimal totalMoney;
    /**
     * '实付总金额',
     */
    private BigDecimal totalPayMoney;
    /**
     * 总运费'
     */
    private BigDecimal totalFreight;
    /**
     * '总购买数量',
     */
    private Integer totalBuyCount;
    /**
     * '支付方式',
     */
    private String payType;
    /**
     * '收货信息-省',
     */
    private String receiverProvince;
    /**
     * '收货信息-城市',
     */
    private String receiverCity;
    /**
     * '收货信息-区县',
     */
    private String receiverCounty;
    /**
     * '收货信息-收货人',
     */
    private String receiverName;
    /**
     * 收货信息-手机号',
     */
    private String receiverPhone;
    /**
     * 收货信息-地址明细',
     */
    private String receiverDetail;
    /**
     * 订单状态:[已取消/待支付/待发货/已发货/确认收货/已完成]',
     */
    private String orderStatus;
    /**
     *  '创建时间',
     */
    private Date createTime;
    /**
     * '支付时间',
     */
    private Date payTime;
    /**
     * '更新时间',
     */
    private Date updateTime;


}
