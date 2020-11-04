package com.dj.mall.platform.vo.order;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderReceiverVoResp implements Serializable {

    /**
     * 收货地址id
     */
    private Integer receiverId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收货人名称
     */
    private String receiverName;
    /**
     * 收货省
     */
    private String receiverProvince;
    /**
     * 收货市
     */
    private String receiverCity;

    /**
     * 收获区县
     */
    private String receiverCounty;
    /**
     * 详细地址
     */
    private String receiverDetail;
    /**
     *  收获手机号
     */
    private String receiverPhone;


}
