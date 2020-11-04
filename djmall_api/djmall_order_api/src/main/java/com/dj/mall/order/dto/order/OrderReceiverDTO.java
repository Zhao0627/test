package com.dj.mall.order.dto.order;

import lombok.Data;
import org.dozer.Mapping;

import java.io.Serializable;

@Data
public class OrderReceiverDTO implements Serializable {
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
