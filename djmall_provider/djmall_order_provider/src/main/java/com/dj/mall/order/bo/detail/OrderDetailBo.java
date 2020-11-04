package com.dj.mall.order.bo.detail;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetailBo implements Serializable {

    private String userName;
    private Integer detailId;
    private String productNameList;
    private String childOrderNo;
    private String productName;
    private Integer buyCount;
    private BigDecimal payMoney;
    private String name;
    private BigDecimal freightPrice;
    private Date createTime;
    private String orderStatus;
    private BigDecimal skuPrice;
    private String skuInfo;
    private Integer skuRate;
    private Date payTime;
    private String parentOrderNo;
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
    private Date cancelTime;
    private Integer buyerId;
    private Integer productId;
    private Integer skuId;
    /**
     * 买家消息
     */
    private String buyerMessage;
    private Integer commentId;
    private Integer commentIsDel;
}
