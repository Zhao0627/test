package com.dj.mall.platform.vo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class OrderDetailVoResp implements Serializable {

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
    //后台到前台转换（”yyyy-MM-dd hh:mm:ss“）格式
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    //前台到后台转换Date格式
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
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
     * 买家消息
     */
    private String buyerMessage;

    /**
     * 支付时间
     */
    //后台到前台转换（”yyyy-MM-dd hh:mm:ss“）格式
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    //前台到后台转换Date格式
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date cancelTime;

    /**
     * 支付时间
     */
    //后台到前台转换（”yyyy-MM-dd hh:mm:ss“）格式
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
    //前台到后台转换Date格式
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date payTime;

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

    private String createTimeShow;
    private String payTimeShow;

    private BigDecimal orderTotalFreight;
    private BigDecimal orderTotalMoney;
    private BigDecimal orderTotalPayMoney;
    private Integer orderTotalBuyCount;
    private Integer commentId;
    private Integer commentIsDel;
}
